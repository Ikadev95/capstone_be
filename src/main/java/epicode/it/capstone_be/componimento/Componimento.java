package epicode.it.capstone_be.componimento;

import epicode.it.capstone_be.auth.AppUser;
import epicode.it.capstone_be.categoria.Categoria;
import epicode.it.capstone_be.utente.Utente;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "componimenti")
@Data
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
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