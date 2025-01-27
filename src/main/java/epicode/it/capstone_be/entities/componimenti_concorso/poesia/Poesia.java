package epicode.it.capstone_be.entities.componimenti_concorso.poesia;


import epicode.it.capstone_be.entities.componimenti_concorso.componimento.Componimento;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Poesia extends Componimento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String testo;
}