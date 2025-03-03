package epicode.it.capstone_be.entities.componimenti_concorso.poesia;

import epicode.it.capstone_be.auth.requests_responses.AppUserRequest;
import epicode.it.capstone_be.entities.categoria.CategoriaRequest;
import jakarta.persistence.Lob;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PoesiaResponse {
    private Long id;
    private String titolo;
    private LocalDate data_inserimento;
    private AppUserRequest user;
    private CategoriaRequest categoria;
    @Lob
    private String testo;
}
