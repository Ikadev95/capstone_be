package epicode.it.capstone_be.entities.utente;

import epicode.it.capstone_be.auth.AppUser;
import epicode.it.capstone_be.auth.AppUserRepository;
import epicode.it.capstone_be.auth.Role;
import epicode.it.capstone_be.auth.requests_responses.RegisterJudgeRequest;
import epicode.it.capstone_be.auth.requests_responses.RegisterRequest;
import epicode.it.capstone_be.entities.pagamento.PagamentoRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
@Validated
public class UtenteService {
    private final UtenteRepository utenteRepository;
    private final PagamentoRepo pagamentoRepo;


    //restituisco tutti gli utenti
    public List<Utente> findAllUsers(){ return utenteRepository.findAll();}

    public Page<UtenteAnnoResponse> getAllPaged(Pageable pageable) {
        return utenteRepository.findAllUsersPaged(pageable);
    }

    public Utente getUtenteByUsername(User userDetails){
        return utenteRepository.findByUsername(userDetails.getUsername());
    }

    public Page<UtenteAnnoResponse> findUsersByPagamentoAnno(int anno, Pageable pageable) {
        return pagamentoRepo.findUsersByPagamentoAnno(anno, pageable);
    }

    public Page<RegisterJudgeResponse> getAllPagedByJudge(Pageable pageable) {
        return utenteRepository.findUsersByRole(Role.ROLE_JUDGE, pageable);
    }
    @Transactional
    public Boolean updateUser(UserDetails userDetails, UtenteRequest utenteRequest, MultipartFile file) throws IOException {

        Utente utente = utenteRepository.findByUsername(userDetails.getUsername());
        if (utente == null) {
            throw new EntityNotFoundException("Utente non trovato");
        }

        boolean modificato = false;

        if (!Objects.equals(utente.getNome(), utenteRequest.getNome())) {
            utente.setNome(utenteRequest.getNome());
            modificato = true;
        }
        if (!Objects.equals(utente.getCognome(), utenteRequest.getCognome())) {
            utente.setCognome(utenteRequest.getCognome());
            modificato = true;
        }
        if (!Objects.equals(utente.getEmail(), utenteRequest.getEmail())) {
            utente.setEmail(utenteRequest.getEmail());
            modificato = true;
        }
        if (!Objects.equals(utente.getTelefono(), utenteRequest.getNumero_telefono())) {
            utente.setTelefono(utenteRequest.getNumero_telefono());
            modificato = true;
        }

        if (file != null && !file.isEmpty()) {
            String filePath = salvaFile(file);
            utente.setAvatar(filePath);
            modificato = true;
        }

        if (modificato) {
            utenteRepository.save(utente);
        }

        return modificato;
    }

    private String salvaFile(MultipartFile file) throws IOException {
        String uploadDir = "uploads/avatar"; // Cartella di destinazione
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Genera un nome univoco
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);

        // Scrive il file su disco
        Files.write(filePath, file.getBytes());

        return filePath.toString();
    }

    //restituisco utente per id
    public Utente getUtenteById(Long id){
        Utente u = utenteRepository.findById(id).orElse(null);
        if(u == null){
            throw new EntityNotFoundException("Utente non trovato");}
        return u;}

    //restituisco utente per email
    public Utente getUtenteByEmail(String email){
        Utente u = utenteRepository.findByEmail(email);
        if(u == null){
            throw new EntityNotFoundException("Utente non trovato");}
        return u; }

}
