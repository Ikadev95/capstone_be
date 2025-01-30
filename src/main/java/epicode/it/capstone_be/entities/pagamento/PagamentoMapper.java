package epicode.it.capstone_be.entities.pagamento;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PagamentoMapper {
    ModelMapper modelMapper = new ModelMapper();

    public PagamentoResponse mapPagamento(Pagamento pagamento) {
        PagamentoResponse pagamentoResponse = modelMapper.map(pagamento, PagamentoResponse.class);
        return pagamentoResponse;
    }

    public List<PagamentoResponse> mapPagamentoResponseList(List<Pagamento> pagamenti) {
        return pagamenti.stream().map(this::mapPagamento).toList();
    }
}
