package epicode.it.capstone_be.entities.componimenti_concorso.fotografia;

import epicode.it.capstone_be.entities.componimenti_concorso.componimento.Componimento;
import epicode.it.capstone_be.entities.componimenti_concorso.componimento.ComponimentoResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FotografiaMapper {
    private ModelMapper modelMapper = new ModelMapper();

    public FotografiaResponse mapFotografia(Fotografia fotografia) {
        FotografiaResponse fotografiaResponse = modelMapper.map(fotografia, FotografiaResponse.class);
        return fotografiaResponse;
    }

    public List<FotografiaResponse> mapFotografiaResponseList(List<Fotografia> componimenti) {
        return componimenti.stream().map(this::mapFotografia).toList();
    }
}
