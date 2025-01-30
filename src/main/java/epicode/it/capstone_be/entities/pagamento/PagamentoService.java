package epicode.it.capstone_be.entities.pagamento;

import epicode.it.capstone_be.auth.AppUser;
import epicode.it.capstone_be.auth.AppUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PagamentoService {
   private final PagamentoRepo pagamentoRepo;
   private final AppUserRepository appUserRepo;

   public Pagamento savePagamento(PagamentoRequest pagamento) {
      AppUser u = appUserRepo.findById(pagamento.getId_user()).orElseThrow(() -> new RuntimeException("Utente non trovato"));
       Pagamento p = new Pagamento();
       BeanUtils.copyProperties(pagamento, p);
       p.setUser(u);
       return pagamentoRepo.save(p);
   }

   public boolean deletePagamento(Long id) {
      if (!pagamentoRepo.existsById(id)) {
         throw new EntityNotFoundException("Pagamento non trovato");
      }
      pagamentoRepo.deleteById(id);
      return true;
   }
}
