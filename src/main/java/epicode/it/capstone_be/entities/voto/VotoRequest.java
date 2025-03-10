package epicode.it.capstone_be.entities.voto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VotoRequest {
    @JsonProperty("voto")
    private Float voto;
    private Long id_componimento;

    public Float getVoto() {
        return voto;
    }

    public void setVoto(Float voto) {
        this.voto = voto;
    }

    public Long getId_componimento() {
        return id_componimento;
    }

    public void setId_componimento(Long id_componimento) {
        this.id_componimento = id_componimento;
    }
}
