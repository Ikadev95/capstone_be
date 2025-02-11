package epicode.it.capstone_be.entities.utente;

import epicode.it.capstone_be.auth.Role;
import epicode.it.capstone_be.auth.requests_responses.RegisterJudgeRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
    boolean existsByEmail(String email);
    Utente findByEmail(String email);
    @Query("SELECT new epicode.it.capstone_be.entities.utente.RegisterJudgeResponse(" +
            "u.id, u.username, ut.nome, ut.cognome, ut.email) " +
            "FROM AppUser u " +
            "JOIN u.utente ut " +
            "WHERE :role MEMBER OF u.roles")
    Page<RegisterJudgeResponse> findUsersByRole(@Param("role") Role role, Pageable pageable);

}
