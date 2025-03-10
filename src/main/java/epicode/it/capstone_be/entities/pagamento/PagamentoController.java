package epicode.it.capstone_be.entities.pagamento;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class PagamentoController {
    @Autowired
    private PagamentoService pagamentoService;
    @Autowired
    private PagamentoMapper mapper;

    @GetMapping("api/pagamenti/user")
    public ResponseEntity<List<PagamentoResponse>> getPagamenti(@AuthenticationPrincipal UserDetails userDetails){
        List<Pagamento> pagamenti = pagamentoService.getPagamentiByUsername(userDetails);
        return ResponseEntity.ok(mapper.mapPagamentoResponseList(pagamenti));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("api/pagamenti/userFromAdmin")
    public ResponseEntity<List<PagamentoResponse>> getPagamentiUserFromAdmin(@RequestParam("id") Long id){
        List<Pagamento> pagamenti = pagamentoService.getPagamentiById(id);
        return ResponseEntity.ok(mapper.mapPagamentoResponseList(pagamenti));
    }

}
