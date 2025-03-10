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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome_categoria() {
        return nome_categoria;
    }

    public void setNome_categoria(String nome_categoria) {
        this.nome_categoria = nome_categoria;
    }

    public Sezioni getSezione() {
        return sezione;
    }

    public void setSezione(Sezioni sezione) {
        this.sezione = sezione;
    }

    public List<Componimento> getComponimenti() {
        return componimenti;
    }

    public void setComponimenti(List<Componimento> componimenti) {
        this.componimenti = componimenti;
    }

    public List<AppUser> getGiudici() {
        return giudici;
    }

    public void setGiudici(List<AppUser> giudici) {
        this.giudici = giudici;
    }
}