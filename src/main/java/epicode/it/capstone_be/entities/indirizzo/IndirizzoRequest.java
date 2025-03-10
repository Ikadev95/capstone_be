package epicode.it.capstone_be.entities.indirizzo;

import epicode.it.capstone_be.entities.comune.ComuneRequest;
import lombok.Data;

@Data
public class IndirizzoRequest {
    private String via;
    private String civico;
    private Long comune_id;

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCivico() {
        return civico;
    }

    public void setCivico(String civico) {
        this.civico = civico;
    }

    public Long getComune_id() {
        return comune_id;
    }

    public void setComune_id(Long comune_id) {
        this.comune_id = comune_id;
    }
}
