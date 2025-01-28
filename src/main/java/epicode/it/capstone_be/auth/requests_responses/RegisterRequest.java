package epicode.it.capstone_be.auth.requests_responses;

import epicode.it.capstone_be.entities.comune.Comune;
import epicode.it.capstone_be.entities.comune.ComuneRequest;
import epicode.it.capstone_be.entities.indirizzo.Indirizzo;
import epicode.it.capstone_be.entities.indirizzo.IndirizzoRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterRequest {
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
    private String telefono;
    private boolean privacy;
    private LocalDate data_di_nascita;
    private IndirizzoRequest indirizzo;
    private Long comune_di_nascita_id;


}
