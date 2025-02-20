package epicode.it.capstone_be.entities.pagamento;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class PagamentoController {
    private final PagamentoService pagamentoService;
    private final PagamentoMapper mapper;

    @GetMapping("api/pagamenti/user")
    public ResponseEntity<List<PagamentoResponse>> getPagamenti(@AuthenticationPrincipal UserDetails userDetails){
        List<Pagamento> pagamenti = pagamentoService.getPagamentiByUsername(userDetails);
        return ResponseEntity.ok(mapper.mapPagamentoResponseList(pagamenti));
    }

}
