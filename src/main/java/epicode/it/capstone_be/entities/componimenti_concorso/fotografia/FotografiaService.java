package epicode.it.capstone_be.entities.componimenti_concorso.fotografia;

import epicode.it.capstone_be.auth.AppUser;
import epicode.it.capstone_be.auth.AppUserRepository;
import epicode.it.capstone_be.entities.categoria.Categoria;
import epicode.it.capstone_be.entities.categoria.CategoriaRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FotografiaService {

    @Autowired
    private  FotografiaRepo fotografiaRepo;

    private final String UPLOAD_DIR = "uploads/fotografie/";

    @Autowired
    private  AppUserRepository appUserRepo;
    @Autowired
    private  CategoriaRepo categoriaRepo;


    public Fotografia salvaFotografia(MultipartFile file, FotografiaRequest fotografiaRequest) throws IOException {
        // Crea la cartella se non esiste
        File directory = new File(UPLOAD_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Genera un nome univoco per il file
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + fileName);

        // Salva il file nel server
        Files.write(filePath, file.getBytes());
        if(!appUserRepo.existsByUsername(fotografiaRequest.getUsername())) {
            throw new EntityNotFoundException("Utente non trovato");
        }
        if(!categoriaRepo.existsById(fotografiaRequest.getId_categoria())) {
            throw new EntityNotFoundException("Categoria non trovata");
        }
        AppUser user = appUserRepo.findByUsername(fotografiaRequest.getUsername()).get();
        Categoria c = categoriaRepo.findById(fotografiaRequest.getId_categoria()).get();

        // Salva il percorso nel database
        Fotografia fotografia = new Fotografia();
        fotografia.setEstensioneFile(getFileExtension(file.getOriginalFilename()));
        if(fotografia.getEstensioneFile().equals("jpg") || fotografia.getEstensioneFile().equals("png") || fotografia.getEstensioneFile().equals("jpeg")) {
            fotografia.setPercorsoFile(filePath.toString());
            fotografia.setTitolo(fotografiaRequest.getTitolo());
            fotografia.setData_inserimento(LocalDate.now());
            fotografia.setUser(user);
            fotografia.setCategoria(c);
        }
        else {
            throw new EntityNotFoundException("Estensione file non valida");
        }

        return fotografiaRepo.save(fotografia);
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public Fotografia getFotografia(Long id) {
        if(!fotografiaRepo.existsById(id)) {
            throw new EntityNotFoundException("Fotografia non trovata");
    }
        return fotografiaRepo.findById(id).orElse(null);
    }

    @Transactional
    public void deleteFotografia(Long id) {
        Fotografia fotografia = fotografiaRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fotografia non trovata"));

        Path filePath = Paths.get(fotografia.getPercorsoFile());
        try {
            fotografiaRepo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("La fotografia è ancora collegata ad altri elementi e non può essere eliminata.");
        }

        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new IllegalStateException("Errore durante l'eliminazione del file: " + e.getMessage());
        }

    }

    public List<Fotografia> getAllFotografie() {return fotografiaRepo.findAll();}

    public List<Fotografia> getFotografieByUser( UserDetails userDetails) {
        return fotografiaRepo.findByUsername(userDetails.getUsername());
    }

    public Page<FotografiaProjection> getFotografieByCategoria(String nomeCategoria, Pageable pageable) {
        return fotografiaRepo.findFotografieByCategoria(nomeCategoria, pageable);
    }
}
