package epicode.it.capstone_be.entities.componimenti_concorso.fotografia;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FotografiaService {
    private final FotografiaRepo fotografiaRepo;

    public Fotografia createFotografia(FotografiaRequest foto) {
        Fotografia fotografia = new Fotografia();
        BeanUtils.copyProperties(foto, fotografia);
        return fotografiaRepo.save(fotografia);
    }

    public Fotografia getFotografia(Long id) {
        if(!fotografiaRepo.existsById(id)) {
            throw new EntityNotFoundException("Fotografia non trovata");
    }
        return fotografiaRepo.findById(id).orElse(null);
    }

    public Fotografia updateFotografia(Fotografia fotografia) {
        return fotografiaRepo.save(fotografia);
    }

    public void deleteFotografia(Long id) {
        if (!fotografiaRepo.existsById(id)) {
            throw new EntityNotFoundException("Fotografia non trovata");
        }
        fotografiaRepo.deleteById(id);
    }

    public List<Fotografia> getAllFotografie() {return fotografiaRepo.findAll();}
}
