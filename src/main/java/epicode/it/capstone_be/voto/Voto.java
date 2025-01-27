package epicode.it.capstone_be.voto;

import epicode.it.capstone_be.auth.AppUser;

import epicode.it.capstone_be.componimenti_concorso.componimento.Componimento;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "voti")
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private int voto;

    @OneToOne
    @JoinColumn(name = "componimento_id")
    private Componimento componimento;

    @JoinColumn(name = "user_id")
    @OneToOne
    private AppUser user;


}