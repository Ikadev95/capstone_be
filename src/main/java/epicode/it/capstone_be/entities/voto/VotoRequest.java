package epicode.it.capstone_be.entities.voto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VotoRequest {
    @JsonProperty("voto")
    private Float voto;
    private Long id_componimento;
}
