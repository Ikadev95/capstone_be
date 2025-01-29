package epicode.it.capstone_be.entities.componimenti_concorso.fotografia;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FotografiaService {
    private final FotografiaRepo fotografiaRepo;
    private final String UPLOAD_DIR = "uploads/fotografie/";


    public Fotografia salvaFotografia(MultipartFile file) throws IOException {
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

        // Salva il percorso nel database
        Fotografia fotografia = new Fotografia();
        fotografia.setEstensioneFile(getFileExtension(file.getOriginalFilename()));
        fotografia.setPercorsoFile(filePath.toString());

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

    public Fotografia updateFotografia(Fotografia fotografia) {
        return fotografiaRepo.save(fotografia);
    }

    public void deleteFotografia(Long id) {
        if (!fotografiaRepo.existsById(id)) {
            throw new EntityNotFoundException("Fotografia non trovata");
        }
        fotografiaRepo.deleteById(id);
    }

    public List<Fotografia> getAllFotografie() {return fotografiaRepo.findAll();}
}
