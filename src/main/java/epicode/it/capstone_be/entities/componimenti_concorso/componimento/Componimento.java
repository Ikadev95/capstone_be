package epicode.it.capstone_be.entities.componimenti_concorso.componimento;

import epicode.it.capstone_be.auth.AppUser;
import epicode.it.capstone_be.entities.categoria.Categoria;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "componimenti")
@Data
@Inheritance (strategy = InheritanceType.JOINED)
@DiscriminatorColumn (name = "tipo_componimento")
@NoArgsConstructor

public abstract class Componimento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String titolo;
    private LocalDate data_inserimento;

    @ManyToOne
    @JoinColumn (name = "categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private AppUser user;




}