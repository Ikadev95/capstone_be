package epicode.it.capstone_be.entities.componimenti_concorso.poesia;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PoesiaMapper {

    ModelMapper modelMapper = new ModelMapper();

    public PoesiaResponse mapPoesia(Poesia poesia) {
        PoesiaResponse poesiaResponse = modelMapper.map(poesia, PoesiaResponse.class);
        return poesiaResponse;
    }

    public List<PoesiaResponse> mapPoesiaResponseList(List<Poesia> componimenti) {
        return componimenti.stream().map(this::mapPoesia).toList();
    }
}
