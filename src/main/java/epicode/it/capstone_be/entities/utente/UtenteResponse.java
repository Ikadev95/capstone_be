package epicode.it.capstone_be.entities.utente;

import epicode.it.capstone_be.auth.requests_responses.AppUserRequest;
import epicode.it.capstone_be.auth.requests_responses.LoginRequest;
import epicode.it.capstone_be.entities.comune.ComuneRequest;
import epicode.it.capstone_be.entities.indirizzo.IndirizzoRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UtenteResponse {
    @NotBlank
    private String nome;
    @NotBlank
    private String cognome;
    @NotBlank @Email
    private String email;
    @NotNull
    private LocalDate data_di_nascita;
    private String telefono;
    private String avatar;
    private boolean privacy;
    private IndirizzoRequest indirizzo;
    private ComuneRequest comune_di_nascita;
    private AppUserRequest appUser;

}
