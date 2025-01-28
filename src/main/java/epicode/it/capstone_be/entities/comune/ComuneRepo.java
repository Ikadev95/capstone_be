package epicode.it.capstone_be.entities.comune;

import epicode.it.capstone_be.entities.provincia.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComuneRepo extends JpaRepository <Comune, Long> {
    public List<Comune> findByProvincia(Provincia provincia);
}
