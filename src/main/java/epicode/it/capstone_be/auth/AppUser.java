package epicode.it.capstone_be.auth;


import epicode.it.capstone_be.entities.categoria.Categoria;
import epicode.it.capstone_be.entities.componimenti_concorso.componimento.Componimento;
import epicode.it.capstone_be.entities.pagamento.Pagamento;
import epicode.it.capstone_be.entities.utente.Utente;
import jakarta.persistence.*;
import lombok.Data;


import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL)
    private Utente utente;

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    private List<Componimento> componimenti;

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Pagamento> pagamenti;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public List<Componimento> getComponimenti() {
        return componimenti;
    }

    public void setComponimenti(List<Componimento> componimenti) {
        this.componimenti = componimenti;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
