package epicode.it.capstone_be.entities.comune;

import epicode.it.capstone_be.entities.provincia.Provincia;
import epicode.it.capstone_be.entities.provincia.ProvinciaRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ComuneService {
    private final ComuneRepo comuneRepo;
    private final ProvinciaRepo provinciaRepo;

    public List<Comune> getComuneByProvincia(Long id_provincia) {
        Provincia provincia = provinciaRepo.findById(id_provincia).orElse(null);
        if(provincia == null) {
            throw new EntityNotFoundException("Provincia non trovata");
        }
        return comuneRepo.findByProvincia(provincia);
    }
}
