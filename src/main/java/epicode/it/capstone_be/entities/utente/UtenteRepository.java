package epicode.it.capstone_be.entities.utente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
    boolean existsByEmail(String email);
}
