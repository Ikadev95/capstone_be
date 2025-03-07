package epicode.it.capstone_be.stripe;

import lombok.Data;

@Data
public class PaymentItemCash {
    private String name;
    private int amount;
    private String sezione;
    private int numeroComponimenti;
    private String ragione;
    private Long id;
}
