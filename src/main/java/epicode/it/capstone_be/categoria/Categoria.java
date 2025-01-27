package epicode.it.capstone_be.categoria;

import epicode.it.capstone_be.auth.AppUser;
import epicode.it.capstone_be.componimento.Componimento;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "categorie")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome_categoria;
    private String descrizione;

   @OneToMany (mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Componimento> componimenti;
}