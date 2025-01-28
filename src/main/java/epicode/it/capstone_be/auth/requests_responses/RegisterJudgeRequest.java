package epicode.it.capstone_be.auth.requests_responses;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterJudgeRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String nome;
    @NotBlank
    private String cognome;
    @NotBlank @Email
    private String email;
}
