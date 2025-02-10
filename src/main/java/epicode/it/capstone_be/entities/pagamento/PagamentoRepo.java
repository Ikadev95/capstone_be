package epicode.it.capstone_be.entities.pagamento;

import epicode.it.capstone_be.auth.AppUser;
import epicode.it.capstone_be.entities.utente.UtenteAnnoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PagamentoRepo extends JpaRepository<Pagamento,Long> {
    @Query("SELECT p FROM Pagamento p WHERE p.user.username = :username")
    List<Pagamento> findPagamentoByUsername(@Param("username") String username);

    @Query("SELECT p FROM Pagamento p WHERE p.user = :user AND p.data_pagamento BETWEEN :startDate AND :endDate")
    List<Pagamento> findPagamentiByUserAndDateRange(@Param("user") AppUser user,
                                                    @Param("startDate") LocalDate startDate,
                                                    @Param("endDate") LocalDate endDate);

    @Query("SELECT p FROM Pagamento p WHERE p.user = :user AND p.ragione_pagamento = :ragione AND p.data_pagamento BETWEEN :startDate AND :endDate")
    List<Pagamento> findPagamentiByUserAndRagioneAndDateRange(@Param("user") AppUser user,
                                                              @Param("ragione") RagionePagamentoEnum ragione,
                                                              @Param("startDate") LocalDate startDate,
                                                              @Param("endDate") LocalDate endDate);

    @Query("SELECT new epicode.it.capstone_be.entities.utente.UtenteAnnoResponse(" +
            "u.id, u.username, ut.nome, ut.cognome, ut.email) " +
            "FROM Pagamento p " +
            "JOIN p.user u " +
            "JOIN u.utente ut " +
            "WHERE EXTRACT(YEAR FROM p.data_pagamento) = :anno")
    Page<UtenteAnnoResponse> findUsersByPagamentoAnno(@Param("anno") int anno, Pageable pageable);

}
