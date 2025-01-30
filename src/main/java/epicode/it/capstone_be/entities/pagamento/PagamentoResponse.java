package epicode.it.capstone_be.entities.pagamento;

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
}
