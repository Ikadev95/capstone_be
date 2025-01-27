package epicode.it.capstone_be.componimenti_concorso.fotografia;

import epicode.it.capstone_be.componimenti_concorso.componimento.Componimento;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Fotografia extends Componimento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String estensioneFile;

}