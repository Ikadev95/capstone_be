package epicode.it.capstone_be.entities.comune;

import epicode.it.capstone_be.entities.provincia.ProvinciaRequest;
import lombok.Data;

@Data
public class ComuneResponse {
    private Long id;
    private String nome_comune;
    private String cap;
    private ProvinciaRequest provincia;
}
