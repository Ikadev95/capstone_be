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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getData_inserimento() {
        return data_inserimento;
    }

    public void setData_inserimento(LocalDate data_inserimento) {
        this.data_inserimento = data_inserimento;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}