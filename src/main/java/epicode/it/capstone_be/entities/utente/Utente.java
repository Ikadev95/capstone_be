package epicode.it.capstone_be.entities.utente;

import epicode.it.capstone_be.auth.AppUser;
import epicode.it.capstone_be.entities.comune.Comune;
import epicode.it.capstone_be.entities.indirizzo.Indirizzo;
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
    @JoinColumn(name = "indirizzo_id", referencedColumnName = "id")
    private Indirizzo indirizzo;

    @OneToOne
    @JoinColumn(name ="user_id", nullable = false, unique = true)
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "luogo_di_nascita_id")
    private Comune luogo_di_nascita;

}