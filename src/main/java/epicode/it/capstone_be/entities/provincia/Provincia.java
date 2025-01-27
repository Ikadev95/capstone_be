package epicode.it.capstone_be.entities.provincia;

import epicode.it.capstone_be.entities.comune.Comune;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "province")
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome_provincia;
    private String sigla;
    private String regione;
    private String zona;

    @OneToMany(mappedBy = "provincia")
    private List<Comune> comuni;

}