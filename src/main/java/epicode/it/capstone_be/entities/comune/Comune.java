package epicode.it.capstone_be.entities.comune;

import epicode.it.capstone_be.entities.indirizzo.Indirizzo;
import epicode.it.capstone_be.entities.provincia.Provincia;
import epicode.it.capstone_be.entities.utente.Utente;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "comuni")
public class Comune {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome_comune;
    private String cap;

    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;

    @OneToMany(mappedBy = "comune")
    private List<Indirizzo> indirizzi;


}