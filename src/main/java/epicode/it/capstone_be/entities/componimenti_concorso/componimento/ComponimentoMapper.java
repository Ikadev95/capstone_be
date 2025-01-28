package epicode.it.capstone_be.entities.componimenti_concorso.componimento;

import epicode.it.capstone_be.entities.categoria.CategoriaResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ComponimentoMapper {
    private ModelMapper modelMapper = new ModelMapper();

    public ComponimentoResponse mapComponimento(Componimento componimento) {
        ComponimentoResponse componimentoResponse = modelMapper.map(componimento, ComponimentoResponse.class);
        return componimentoResponse;
    }

    public List<ComponimentoResponse> mapComponimentoResponseList(List<Componimento> componimenti) {
        return componimenti.stream().map(this::mapComponimento).toList();
    }
}
