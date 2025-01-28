package epicode.it.capstone_be.entities.utente;

import epicode.it.capstone_be.auth.requests_responses.RegisterRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@AllArgsConstructor
@Validated
public class UtenteService {
    private final UtenteRepository utenteRepository;

    //restituisco tutti gli utenti
    public List<Utente> findAllUsers(){ return utenteRepository.findAll();}

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
