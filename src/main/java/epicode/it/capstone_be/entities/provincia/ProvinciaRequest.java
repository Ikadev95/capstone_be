package epicode.it.capstone_be.entities.provincia;

import lombok.Data;

@Data
public class ProvinciaRequest {
    private String nome_provincia;
    private String sigla;
    private String regione;
    private String zona;
}
