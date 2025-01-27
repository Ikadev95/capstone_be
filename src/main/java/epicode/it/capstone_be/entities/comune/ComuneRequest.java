package epicode.it.capstone_be.entities.comune;

import epicode.it.capstone_be.entities.provincia.ProvinciaRequest;
import lombok.Data;

@Data
public class ComuneRequest {
    private String nome_comune;
    private int cap;
    private ProvinciaRequest provincia;
}
