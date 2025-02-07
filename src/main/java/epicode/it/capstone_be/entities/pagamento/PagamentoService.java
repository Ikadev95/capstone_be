package epicode.it.capstone_be.entities.pagamento;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PagamentoService {
    private final PagamentoRepo pagamentoRepo;

    public List<Pagamento> getPagamentiByUsername(UserDetails userDetails) {
        return pagamentoRepo.findPagamentoByUsername(userDetails.getUsername());
    }

}
