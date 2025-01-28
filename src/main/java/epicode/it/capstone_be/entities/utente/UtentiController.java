package epicode.it.capstone_be.entities.utente;

import epicode.it.capstone_be.auth.requests_responses.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/utenti")
public class UtentiController {

    private final UtenteService utenteService;
    private final UtenteMapper mapper;

    @GetMapping
    public ResponseEntity<List<UtenteResponse>> getUtenti(){
        List<Utente> utenti = utenteService.findAllUsers();
        return ResponseEntity.ok(mapper.mapUtenteResponseList(utenti));
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
