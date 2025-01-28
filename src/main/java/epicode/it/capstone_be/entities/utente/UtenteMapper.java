package epicode.it.capstone_be.entities.utente;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UtenteMapper {
    private ModelMapper modelMapper = new ModelMapper();

    public UtenteResponse mapUtente(Utente utente) {
        UtenteResponse utenteResponse = modelMapper.map(utente, UtenteResponse.class);
        utenteResponse.getIndirizzo().setComune_id(utente.getIndirizzo().getComune().getId());
        return utenteResponse;
    }



   public List<UtenteResponse> mapUtenteResponseList(List<Utente> utenti) {
       return utenti.stream().map(this::mapUtente).toList();
   }
}
