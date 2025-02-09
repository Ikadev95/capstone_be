package epicode.it.capstone_be.entities.utente;

import epicode.it.capstone_be.auth.requests_responses.RegisterRequest;
import epicode.it.capstone_be.entities.pagamento.PagamentoRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Validated
public class UtenteService {
    private final UtenteRepository utenteRepository;
    private final UtenteMapper mapper;
    private final PagamentoRepo pagamentoRepo;

    //restituisco tutti gli utenti
    public List<Utente> findAllUsers(){ return utenteRepository.findAll();}

    public Page<UtenteResponse> getAllPageable(Pageable pageable) {
        Page<Utente> pagedInvoices = utenteRepository.findAll(pageable);
        Page<UtenteResponse> response = pagedInvoices.map(e -> {
            UtenteResponse invoiceResponse = mapper.mapUtente(e);
            return invoiceResponse;
        });
        return response;
    }

    public Page<UtenteAnnoResponse> findUsersByPagamentoAnno(int anno, Pageable pageable) {
        return pagamentoRepo.findUsersByPagamentoAnno(anno, pageable);
    }

    //restituisco utente per id
    public Utente getUtenteById(Long id){
        Utente u = utenteRepository.findById(id).orElse(null);
        if(u == null){
            throw new EntityNotFoundException("Utente non trovato");}
        return u;}

    //restituisco utente per email
    public Utente getUtenteByEmail(String email){
        Utente u = utenteRepository.findByEmail(email);
        if(u == null){
            throw new EntityNotFoundException("Utente non trovato");}
        return u; }

}
