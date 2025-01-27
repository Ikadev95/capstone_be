package epicode.it.capstone_be.utente;

import epicode.it.capstone_be.auth.AppUser;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "utenti")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;
    private String cognome;
    private String email;
    private LocalDate data_di_nascita;
    private String telefono;
    private String avatar;
    private boolean privacy;

    @OneToOne
    @JoinColumn(name ="user_id", nullable = false, unique = true)
    private AppUser appUser;

}