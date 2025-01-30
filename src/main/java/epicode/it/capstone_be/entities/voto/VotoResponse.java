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
}
