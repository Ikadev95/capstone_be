package epicode.it.capstone_be.entities.categoria;


import epicode.it.capstone_be.auth.AppUser;
import epicode.it.capstone_be.entities.componimenti_concorso.componimento.Componimento;
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

    @Column(name = "nome_categoria")
    private String nome_categoria;
    private Sezioni sezione;

   @OneToMany (mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Componimento> componimenti;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<AppUser> giudici;
}