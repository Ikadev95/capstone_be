package epicode.it.capstone_be.entities.voto;

import epicode.it.capstone_be.entities.utente.Utente;
import epicode.it.capstone_be.entities.utente.UtenteResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class VotoMapper {
    private ModelMapper modelMapper = new ModelMapper();

    public VotoResponse mapVoto(Voto voto) {
        VotoResponse votoResponse = modelMapper.map(voto, VotoResponse.class);

        return votoResponse;
    }



    public List<VotoResponse> mapVotoResponseList(List<Voto> voti) {
        return voti.stream().map(this::mapVoto).toList();
    }
}
