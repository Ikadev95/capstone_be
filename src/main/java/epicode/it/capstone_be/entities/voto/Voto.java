package epicode.it.capstone_be.entities.voto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import epicode.it.capstone_be.auth.AppUser;

import epicode.it.capstone_be.entities.componimenti_concorso.componimento.Componimento;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "voti")
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Float voto;

    @ManyToOne
    @JoinColumn(name = "componimento_id")
    private Componimento componimento;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private AppUser user;



}