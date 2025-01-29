package epicode.it.capstone_be.entities.voto;

import lombok.Data;

@Data
public class VotoRequest {
    private double voto;
    private Long id_componimento;
    private Long id_user;
}
