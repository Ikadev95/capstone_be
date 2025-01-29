package epicode.it.capstone_be.entities.componimenti_concorso.poesia;

import epicode.it.capstone_be.auth.requests_responses.AppUserRequest;
import epicode.it.capstone_be.entities.categoria.CategoriaRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PoesiaRequest {
    @NotBlank
    private String titolo;
    @NotNull
    private Long id_user;
    @NotNull
    private Long id_categoria;
    @NotBlank
    private String testo;
}
