package epicode.it.capstone_be.entities.voto;

import com.fasterxml.jackson.annotation.JsonProperty;
import epicode.it.capstone_be.auth.requests_responses.AppUserRequest;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class VotoResponse {
    @JsonProperty("voto")
    private Float voto;
    private AppUserRequest giudice;

    public Float getVoto() {
        return voto;
    }

    public void setVoto(Float voto) {
        this.voto = voto;
    }

    public AppUserRequest getGiudice() {
        return giudice;
    }

    public void setGiudice(AppUserRequest giudice) {
        this.giudice = giudice;
    }
}
