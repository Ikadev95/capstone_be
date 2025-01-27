package epicode.it.capstone_be.entities.utente;

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
}
