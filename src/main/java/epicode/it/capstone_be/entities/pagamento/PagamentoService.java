package epicode.it.capstone_be.entities.pagamento;

import epicode.it.capstone_be.auth.AppUser;
import epicode.it.capstone_be.auth.AppUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class PagamentoService {
    private final PagamentoRepo pagamentoRepo;
    private final AppUserRepository appUserRepository;

    public List<Pagamento> getPagamentiByUsername(UserDetails userDetails) {
        AppUser appUser = appUserRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));
        return pagamentoRepo.findPagamentiByUserAndDateRange(appUser, LocalDate.of(LocalDate.now().getYear(), 1, 1), LocalDate.now());
    }

    public List<Pagamento> getPagamentiById(Long id) {
        AppUser appUser = appUserRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));
        return pagamentoRepo.findPagamentiByUserAndDateRange(appUser, LocalDate.of(LocalDate.now().getYear(), 1, 1), LocalDate.now());
    }

}
