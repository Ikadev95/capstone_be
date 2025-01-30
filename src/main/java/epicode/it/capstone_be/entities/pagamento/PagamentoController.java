package epicode.it.capstone_be.entities.pagamento;

import epicode.it.capstone_be.entities.componimenti_concorso.poesia.PoesiaMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PagamentoController {
    private final PagamentoService pagamentoService;
    private final PagamentoMapper mapper;

    @PostMapping("/pagamento")
    public ResponseEntity<PagamentoResponse> createPagamento(PagamentoRequest pagamentoRequest) {
        Pagamento pagamento = pagamentoService.savePagamento(pagamentoRequest);
        return ResponseEntity.ok(mapper.mapPagamento(pagamento));
    }

    @DeleteMapping("/pagamento/{id}")
    public ResponseEntity<String> deletePagamento(@Validated @PathVariable Long id){
        pagamentoService.deletePagamento(id);
        return new ResponseEntity<>("Pagamento eliminato con successo", HttpStatus.OK);
    }


}
