package epicode.it.capstone_be.entities.indirizzo;

import epicode.it.capstone_be.entities.comune.ComuneRequest;
import lombok.Data;

@Data
public class IndirizzoRequest {
    private String via;
    private String civico;
    private Long comune_id;
}
