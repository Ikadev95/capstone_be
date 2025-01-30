package epicode.it.capstone_be.entities.provincia;

import epicode.it.capstone_be.entities.pagamento.Pagamento;
import epicode.it.capstone_be.entities.pagamento.PagamentoResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ProvinciaMapper {
    ModelMapper modelMapper = new ModelMapper();

    public ProvinciaResponse mapProvincia(Provincia provincia) {
        ProvinciaResponse provinciaResponse = modelMapper.map(provincia, ProvinciaResponse.class);
        return provinciaResponse;
    }

    public List<ProvinciaResponse> mapProvinciaResponseList(List<Provincia> province) {
        return province.stream().map(this::mapProvincia).toList();
    }
}
