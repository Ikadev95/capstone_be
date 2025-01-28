package epicode.it.capstone_be.entities.componimenti_concorso.componimento;

import epicode.it.capstone_be.auth.requests_responses.AppUserRequest;
import epicode.it.capstone_be.entities.categoria.Categoria;
import epicode.it.capstone_be.entities.categoria.CategoriaResponse;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ComponimentoResponse {
    private String titolo;
    private LocalDate data_inserimento;
    private AppUserRequest user;
    private CategoriaResponse categoria;
}
