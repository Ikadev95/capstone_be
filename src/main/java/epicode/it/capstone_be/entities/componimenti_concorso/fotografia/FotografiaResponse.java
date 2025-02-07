package epicode.it.capstone_be.entities.componimenti_concorso.fotografia;

import epicode.it.capstone_be.auth.requests_responses.AppUserRequest;
import epicode.it.capstone_be.entities.categoria.CategoriaRequest;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FotografiaResponse {
    private String titolo;
    private LocalDate data_inserimento;
    private AppUserRequest user;
    private CategoriaRequest categoria;
    private String estensioneFile;
    private String percorsoFile;
}
