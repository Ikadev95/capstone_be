package epicode.it.capstone_be.entities.componimenti_concorso.poesia;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PoesiaService {
    private final PoesiaRepo poesiaRepo;

    public Poesia getPoesia(Long id) {
        if (!poesiaRepo.existsById(id)) {
            throw new EntityNotFoundException("Poesia non trovata");
        }
        return poesiaRepo.findById(id).get();
    }

    public List<Poesia> getAllPoesie() {
        return poesiaRepo.findAll();
    }

    public Poesia createPoesia(Poesia poesia) {
        return poesiaRepo.save(poesia);
    }

    public Poesia updatePoesia(Poesia poesia) {
        return poesiaRepo.save(poesia);
    }

    public void deletePoesia(Long id) {
        if (!poesiaRepo.existsById(id)) {
            throw new EntityNotFoundException("Poesia non trovata");
        }
        poesiaRepo.deleteById(id);
    }
}
