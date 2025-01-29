package epicode.it.capstone_be.entities.pagamento;

import epicode.it.capstone_be.auth.AppUser;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "pagamenti")
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private LocalDate data_pagamento;
    private String metodo_pagamento;
    private int importo;
    private String stato_pagamento;
    private RagionePagamentoEnum ragione_pagamento;
    private int numero_poesie_pagate;
    private int numero_foto_pagate;


    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser user;


}