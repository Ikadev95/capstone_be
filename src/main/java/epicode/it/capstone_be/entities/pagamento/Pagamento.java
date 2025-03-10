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

    @Enumerated(EnumType.STRING)
    private RagionePagamentoEnum ragione_pagamento;
    private int numero_poesie_pagate;
    private int numero_foto_pagate;

    @Enumerated(EnumType.STRING)
    private StatoPagamentoEnum stato_pagamento;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData_pagamento() {
        return data_pagamento;
    }

    public void setData_pagamento(LocalDate data_pagamento) {
        this.data_pagamento = data_pagamento;
    }

    public String getMetodo_pagamento() {
        return metodo_pagamento;
    }

    public void setMetodo_pagamento(String metodo_pagamento) {
        this.metodo_pagamento = metodo_pagamento;
    }

    public int getImporto() {
        return importo;
    }

    public void setImporto(int importo) {
        this.importo = importo;
    }

    public RagionePagamentoEnum getRagione_pagamento() {
        return ragione_pagamento;
    }

    public void setRagione_pagamento(RagionePagamentoEnum ragione_pagamento) {
        this.ragione_pagamento = ragione_pagamento;
    }

    public int getNumero_poesie_pagate() {
        return numero_poesie_pagate;
    }

    public void setNumero_poesie_pagate(int numero_poesie_pagate) {
        this.numero_poesie_pagate = numero_poesie_pagate;
    }

    public int getNumero_foto_pagate() {
        return numero_foto_pagate;
    }

    public void setNumero_foto_pagate(int numero_foto_pagate) {
        this.numero_foto_pagate = numero_foto_pagate;
    }

    public StatoPagamentoEnum getStato_pagamento() {
        return stato_pagamento;
    }

    public void setStato_pagamento(StatoPagamentoEnum stato_pagamento) {
        this.stato_pagamento = stato_pagamento;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}