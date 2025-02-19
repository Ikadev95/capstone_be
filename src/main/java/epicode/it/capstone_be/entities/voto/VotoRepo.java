package epicode.it.capstone_be.entities.voto;

import epicode.it.capstone_be.auth.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepo extends JpaRepository<Voto, Long> {
    Voto findByComponimentoIdAndUserId(Long componimentoId, Long idGiudice);
}
