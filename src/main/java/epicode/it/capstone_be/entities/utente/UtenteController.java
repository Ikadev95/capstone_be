package epicode.it.capstone_be.entities.utente;

import epicode.it.capstone_be.auth.AppUserService;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("api/utenti")
@PreAuthorize("isAuthenticated()")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;
    @Autowired
    private UtenteMapper mapper;
    @Autowired
    private AppUserService appUserService;

    @GetMapping
    public ResponseEntity<List<UtenteResponse>> getUtenti(){
        List<Utente> utenti = utenteService.findAllUsers();
        return ResponseEntity.ok(mapper.mapUtenteResponseList(utenti));
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<UtenteAnnoResponse>> getAllPaged(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(utenteService.getAllPaged(pageable));
    }

    @GetMapping("/paged/year")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Page<UtenteAnnoResponse>> getAllPagedByYear(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(utenteService.findUsersByPagamentoAnno(LocalDate.now().getYear(), pageable));
    }

    @GetMapping("/paged/judge")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Page<RegisterJudgeResponse>> getAllPagedByJudge(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(utenteService.getAllPagedByJudge(pageable));
    }


    @GetMapping("{id}")
    public ResponseEntity<UtenteResponse> getUtente(@Validated @PathVariable Long id){
        Utente utente = utenteService.getUtenteById(id);
        return ResponseEntity.ok(mapper.mapUtente(utente));
    }

    @GetMapping("me")
    public ResponseEntity<UtenteResponse> getUtente(@AuthenticationPrincipal User userDetails){
        Utente utente = utenteService.getUtenteByUsername(userDetails);
        return ResponseEntity.ok(mapper.mapUtente(utente));
    }

    @GetMapping("email/{email}")
    public ResponseEntity<UtenteResponse> getUtenteByEmail(@Validated @PathVariable String email){
        Utente utente = utenteService.getUtenteByEmail(email);
        return ResponseEntity.ok(mapper.mapUtente(utente));
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<Map <String,String>> deleteUtente(@Validated @PathVariable Long id){
        appUserService.deleteUser(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Utente eliminato con successo");
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/upload/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> uploadAvatar(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("nome") String nome,
            @RequestParam("cognome") String cognome,
            @RequestParam("email") String email,
            @RequestParam("numero_telefono") String numero_telefono,
            @AuthenticationPrincipal UserDetails userDetails
    ) throws IOException {

        // Definisci la dimensione massima consentita in byte
        long MAX_FILE_SIZE = 5 * 1024 * 1024;

        // Definisci le dimensioni massime consentite
        int MAX_WIDTH = 4000;
        int MAX_HEIGHT = 3000;

        // Se il file è presente, verifica la dimensione e la risoluzione
        if (file != null) {
            // Verifica la dimensione del file
            if (file.getSize() > MAX_FILE_SIZE) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Il file è troppo grande. La dimensione massima consentita è di 5 MB.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            // Controlla la risoluzione dell'immagine
            try (InputStream is = file.getInputStream()) {
                BufferedImage image = ImageIO.read(is);
                if (image != null) {
                    int width = image.getWidth();
                    int height = image.getHeight();
                    if (width > MAX_WIDTH || height > MAX_HEIGHT) {
                        Map<String, String> response = new HashMap<>();
                        response.put("message", "La risoluzione dell'immagine è troppo alta. La dimensione massima consentita è "
                                + MAX_WIDTH + "x" + MAX_HEIGHT + "px.");
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                    }
                } else {
                    Map<String, String> response = new HashMap<>();
                    response.put("message", "Il file non è un'immagine valida.");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                }
            }
        }

        // Crea l'oggetto UtenteRequest senza file se non presente
        UtenteRequest utenteRequest = new UtenteRequest(nome, cognome, email, numero_telefono);

        // Se il file è presente, passa anche il file alla logica di aggiornamento
        Boolean resp = utenteService.updateUser(userDetails, utenteRequest, file);

        if (resp) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Avatar e utente salvati con successo");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Aggiornamento fallito");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


}
