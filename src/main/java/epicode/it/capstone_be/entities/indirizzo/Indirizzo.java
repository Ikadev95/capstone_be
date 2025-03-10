package epicode.it.capstone_be.entities.indirizzo;

import epicode.it.capstone_be.entities.comune.Comune;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCivico() {
        return civico;
    }

    public void setCivico(String civico) {
        this.civico = civico;
    }

    public Comune getComune() {
        return comune;
    }

    public void setComune(Comune comune) {
        this.comune = comune;
    }
}