package epicode.it.capstone_be.stripe;

import lombok.Data;

@Data
public class PaymentItem {
    private String name;
    private int amount;
}
