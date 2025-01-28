package epicode.it.capstone_be.entities.utente;

import epicode.it.capstone_be.auth.AppUser;
import epicode.it.capstone_be.auth.requests_responses.LoginRequest;
import epicode.it.capstone_be.entities.comune.Comune;
import epicode.it.capstone_be.entities.comune.ComuneRequest;
import epicode.it.capstone_be.entities.indirizzo.Indirizzo;
import epicode.it.capstone_be.entities.indirizzo.IndirizzoRequest;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UtenteResponse {
    private String nome;
    private String cognome;
    private String email;
    private LocalDate data_di_nascita;
    private String telefono;
    private String avatar;
    private boolean privacy;
    private IndirizzoRequest indirizzo;
    private ComuneRequest comune_di_nascita;
    private LoginRequest appUser;

}
