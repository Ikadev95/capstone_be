package epicode.it.capstone_be.entities.componimenti_concorso.poesia;

import epicode.it.capstone_be.auth.requests_responses.AppUserRequest;
import epicode.it.capstone_be.entities.categoria.CategoriaRequest;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PoesiaResponse {
    private String titolo;
    private LocalDate data_inserimento;
    private AppUserRequest user;
    private CategoriaRequest categoria;
    private String testo;
}
