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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getSezione() {
        return sezione;
    }

    public void setSezione(String sezione) {
        this.sezione = sezione;
    }

    public int getNumeroComponimenti() {
        return numeroComponimenti;
    }

    public void setNumeroComponimenti(int numeroComponimenti) {
        this.numeroComponimenti = numeroComponimenti;
    }

    public String getRagione() {
        return ragione;
    }

    public void setRagione(String ragione) {
        this.ragione = ragione;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
