package epicode.it.capstone_be.auth.requests_responses;

import epicode.it.capstone_be.entities.indirizzo.IndirizzoRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterRequest {

    @NotBlank(message = "Username è obbligatorio")
    private String username;

    @NotBlank(message = "Password è obbligatoria")
    private String password;

    @NotBlank(message = "Nome è obbligatorio")
    private String nome;

    @NotBlank(message = "Cognome è obbligatorio")
    private String cognome;

    @NotBlank(message = "Email è obbligatoria")
    @Email(message = "Email non valida")
    private String email;

    private String telefono;

    @NotNull(message = "La privacy deve essere accettata")
    private Boolean privacy;

    @NotNull(message = "La data di nascita è obbligatoria")
    private LocalDate data_di_nascita;

    @NotNull(message = "Indirizzo è obbligatorio")
    private IndirizzoRequest indirizzo;
}