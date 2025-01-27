package epicode.it.capstone_be.entities.indirizzo;

import epicode.it.capstone_be.entities.comune.ComuneRequest;
import lombok.Data;

@Data
public class IndirizzoRequest {
    private Long id;
    private String via;
    private String civico;
    private ComuneRequest comune;
}
