package epicode.it.capstone_be.entities.utente;

import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/utenti")
@PreAuthorize("hasRole('ADMIN')")
public class UtenteController {

    private final UtenteService utenteService;
    private final UtenteMapper mapper;

    @GetMapping
    public ResponseEntity<List<UtenteResponse>> getUtenti(){
        List<Utente> utenti = utenteService.findAllUsers();
        return ResponseEntity.ok(mapper.mapUtenteResponseList(utenti));
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


}
