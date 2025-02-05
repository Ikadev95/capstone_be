package epicode.it.capstone_be.stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class StripeController {

    @Value("${stripe.secret-key}") // Prendi la chiave segreta da application.properties
    private String stripeSecretKey;

    @PostMapping("/create-checkout-session")
    public ResponseEntity<Map<String, String>> createCheckoutSession(@RequestBody PaymentRequest request) {
        Stripe.apiKey = stripeSecretKey;

        List<SessionCreateParams.LineItem> items = new ArrayList<>();
        for (PaymentItem item : request.getItems()) {
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

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:4200/success")  // Pagina di successo
                .setCancelUrl("http://localhost:4200/cancel")  // Pagina di annullamento
                .addAllLineItem(items)
                .build();

        try {
            Session session = Session.create(params);
            return ResponseEntity.ok(Map.of("id", session.getId()));
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
        }
    }
}