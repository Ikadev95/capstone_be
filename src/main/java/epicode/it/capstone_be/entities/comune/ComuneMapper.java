package epicode.it.capstone_be.entities.comune;

import epicode.it.capstone_be.entities.componimenti_concorso.poesia.Poesia;
import epicode.it.capstone_be.entities.componimenti_concorso.poesia.PoesiaResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ComuneMapper {

    ModelMapper modelMapper = new ModelMapper();

    public ComuneResponse mapComune(Comune comune) {
        ComuneResponse comuneResponse = modelMapper.map(comune, ComuneResponse.class);
        return comuneResponse;
    }

    public List<ComuneResponse> mapComuneResponseList(List<Comune> comuni) {
        return comuni.stream().map(this::mapComune).toList();
    }
}
