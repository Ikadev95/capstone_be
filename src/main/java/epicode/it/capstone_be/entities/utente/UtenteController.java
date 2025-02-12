package epicode.it.capstone_be.entities.utente;

import epicode.it.capstone_be.auth.AppUserService;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("api/utenti")
@PreAuthorize("hasRole('ADMIN')")
public class UtenteController {

    private final UtenteService utenteService;
    private final UtenteMapper mapper;
    private final AppUserService appUserService;

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


}
