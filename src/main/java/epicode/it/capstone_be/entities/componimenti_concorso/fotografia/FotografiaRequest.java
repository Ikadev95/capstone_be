package epicode.it.capstone_be.entities.componimenti_concorso.fotografia;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FotografiaRequest {
    private String titolo;
    private String username;
    private Long id_categoria;
}
