package epicode.it.capstone_be.indirizzo;

import epicode.it.capstone_be.comune.Comune;
import epicode.it.capstone_be.utente.Utente;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "indirizzi")
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String via;
    private String civico;

    @ManyToOne
    @JoinColumn(name = "comune_id")
    private Comune comune;



}