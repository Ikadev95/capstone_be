package epicode.it.capstone_be.stripe;

import lombok.Data;

import java.util.List;

@Data
public class PaymentRequest {
    private List<PaymentItem> items;
}
