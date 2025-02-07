package epicode.it.capstone_be.stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import epicode.it.capstone_be.auth.AppUser;
import epicode.it.capstone_be.auth.AppUserRepository;
import epicode.it.capstone_be.entities.pagamento.Pagamento;
import epicode.it.capstone_be.entities.pagamento.PagamentoRepo;
import epicode.it.capstone_be.entities.pagamento.RagionePagamentoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class StripeController {

    @Autowired
    private PagamentoRepo pagamentoRepo;
    @Autowired
    private AppUserRepository appUserRepository;


    @Value("${stripe.secret-key}") // Prendi la chiave segreta da application.properties
    private String stripeSecretKey;

    @PostMapping("/create-checkout-session")
    public ResponseEntity<Map<String, String>> createCheckoutSession(@RequestBody PaymentRequest request, @AuthenticationPrincipal UserDetails userDetails) {

        // Definisci l'intervallo di date per l'anno in corso
        LocalDate startOfYear = LocalDate.of(Year.now().getValue(), Month.JANUARY, 1);
        LocalDate endOfYear = LocalDate.of(Year.now().getValue(), Month.DECEMBER, 31);

        // Recupera l'utente dal database
        AppUser user = appUserRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Trova i pagamenti già effettuati per poesia e foto quest'anno
        List<Pagamento> poesiePagamenti = pagamentoRepo.findPagamentiByUserAndRagioneAndDateRange(user, RagionePagamentoEnum.CONCORSO_POESIA, startOfYear, endOfYear);
        List<Pagamento> fotoPagamenti = pagamentoRepo.findPagamentiByUserAndRagioneAndDateRange(user, RagionePagamentoEnum.CONCORSO_FOTOGRAFIA, startOfYear, endOfYear);

        // Imposta la chiave segreta di Stripe
        Stripe.apiKey = stripeSecretKey;
        Pagamento p = new Pagamento();
        List<SessionCreateParams.LineItem> items = new ArrayList<>();

        for (PaymentItem item : request.getItems()) {

            // Verifica se l'utente ha già effettuato il pagamento per la sezione selezionata
            if (item.getRagione().equalsIgnoreCase("CONCORSO_POESIA") && !poesiePagamenti.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "Hai già effettuato un pagamento per il concorso di poesia quest'anno"));
            }

            if (item.getRagione().equalsIgnoreCase("CONCORSO_FOTOGRAFIA") && !fotoPagamenti.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "Hai già effettuato un pagamento per il concorso di fotografia quest'anno"));
            }

            // Crea un nuovo oggetto pagamento
            p.setImporto(item.getAmount());
            p.setData_pagamento(LocalDate.now());
            p.setRagione_pagamento(RagionePagamentoEnum.valueOf(item.getRagione()));
            p.setMetodo_pagamento("carta");

            if ("poesia".equalsIgnoreCase(item.getSezione())) {
                p.setNumero_poesie_pagate(item.getNumeroComponimenti());
                p.setNumero_foto_pagate(0);
            } else {
                p.setNumero_poesie_pagate(0);
                p.setNumero_foto_pagate(item.getNumeroComponimenti());
            }

            p.setUser(user);

            // Aggiungi l'elemento alla lista per Stripe
            items.add(
                    SessionCreateParams.LineItem.builder()
                            .setPriceData(
                                    SessionCreateParams.LineItem.PriceData.builder()
                                            .setCurrency("eur")
                                            .setUnitAmount(item.getAmount() * 100L) // Stripe usa i centesimi
                                            .setProductData(
                                                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                            .setName(item.getName())
                                                            .build()
                                            )
                                            .build()
                            )
                            .setQuantity(1L)
                            .build()
            );
        }

        // Crea la sessione di pagamento Stripe
        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:4200/successPagamento")  // Pagina di successo
                .setCancelUrl("http://localhost:4200/pagamenti")  // Pagina di annullamento
                .addAllLineItem(items)
                .build();

        try {
            // Crea la sessione Stripe e salva il pagamento
            Session session = Session.create(params);
            pagamentoRepo.save(p);
            return ResponseEntity.ok(Map.of("id", session.getId()));
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
        }
    }
}