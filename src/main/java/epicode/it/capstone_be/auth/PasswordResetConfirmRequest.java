package epicode.it.capstone_be.auth;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PasswordResetConfirmRequest {
    @NotBlank(message = "Il token è obbligatorio")
    private String token;

    @NotBlank(message = "La nuova password è obbligatoria")
    private String newPassword;

    @Email(message = "Email non valida")
    private String email;
}