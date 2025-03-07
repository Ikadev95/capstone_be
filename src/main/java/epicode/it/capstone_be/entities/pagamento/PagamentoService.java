package epicode.it.capstone_be.entities.pagamento;

import epicode.it.capstone_be.auth.AppUser;
import epicode.it.capstone_be.auth.AppUserRepository;
import epicode.it.capstone_be.stripe.PaymentItem;
import epicode.it.capstone_be.stripe.PaymentItemCash;
import epicode.it.capstone_be.stripe.PaymentRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

    @Transactional
    public ResponseEntity<Map<String, String>> pagamentoInContanti(PaymentItemCash request) {
        Pagamento p = new Pagamento();
        AppUser appUser = appUserRepository.findById(request.getId()).orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));
        p.setData_pagamento(LocalDate.now());
        p.setImporto(request.getAmount());
        p.setRagione_pagamento(RagionePagamentoEnum.valueOf(request.getRagione()));
        p.setMetodo_pagamento("contanti");
        p.setStato_pagamento(StatoPagamentoEnum.PAGATO);
        p.setUser(appUser);
        if ("poesia".equalsIgnoreCase(request.getSezione())) {
            p.setNumero_poesie_pagate(request.getNumeroComponimenti());
            p.setNumero_foto_pagate(0);
        } else {
            p.setNumero_poesie_pagate(0);
            p.setNumero_foto_pagate(request.getNumeroComponimenti());
        }
        try{
            pagamentoRepo.save(p);
            return ResponseEntity.ok(Map.of("message", "Pagamento effettuato con successo"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Hai già effettuato un pagamento per questa sezione quest'anno"));
        }

    }
}
