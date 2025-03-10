package epicode.it.capstone_be.entities.pagamento;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
@Data
public class PagamentoResponse {

    private LocalDate data_pagamento;
    private String metodo_pagamento;
    private int importo;
    private String stato_pagamento;
    private RagionePagamentoEnum ragione_pagamento;
    private int numero_poesie_pagate;
    private int numero_foto_pagate;
    private Long id_user;

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

    public String getStato_pagamento() {
        return stato_pagamento;
    }

    public void setStato_pagamento(String stato_pagamento) {
        this.stato_pagamento = stato_pagamento;
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

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }
}
