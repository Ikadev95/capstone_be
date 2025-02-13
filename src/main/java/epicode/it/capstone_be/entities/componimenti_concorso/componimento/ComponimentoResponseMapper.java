package epicode.it.capstone_be.entities.componimenti_concorso.componimento;

import epicode.it.capstone_be.entities.componimenti_concorso.fotografia.Fotografia;
import epicode.it.capstone_be.entities.componimenti_concorso.poesia.Poesia;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ComponimentoResponseMapper {
    private ModelMapper modelMapper = new ModelMapper();

    public ComponimentoResponseMapper() {


        // Configurazione per la classe base Componimento
        this.modelMapper.createTypeMap(Componimento.class, ComponimentoFullResponse.class)
                .addMapping(Componimento::getId, ComponimentoFullResponse::setId)
                .addMapping(Componimento::getTitolo, ComponimentoFullResponse::setTitolo)
                .addMapping(src -> src.getCategoria().getId(), ComponimentoFullResponse::setCategoriaId);

        // Mappatura specifica per Fotografia
        this.modelMapper.createTypeMap(Fotografia.class, ComponimentoFullResponse.class)
                .addMappings(mapper -> {
                    mapper.map(Fotografia::getEstensioneFile, ComponimentoFullResponse::setEstensioneFile);
                    mapper.map(Fotografia::getPercorsoFile, ComponimentoFullResponse::setPercorsoFile);
                });

        // Mappatura specifica per Poesia
        this.modelMapper.createTypeMap(Poesia.class, ComponimentoFullResponse.class)
                .addMappings(mapper -> mapper.map(Poesia::getTesto, ComponimentoFullResponse::setTesto));
    }

    // Metodo per mappare un singolo Componimento
    public ComponimentoFullResponse mapComponimento(Componimento componimento) {
        return modelMapper.map(componimento, ComponimentoFullResponse.class);
    }

    // Metodo per mappare una lista di Componimenti
    public List<ComponimentoFullResponse> mapComponimentoResponseList(List<Componimento> componimenti) {
        return componimenti.stream().map(this::mapComponimento).toList();
    }
}
