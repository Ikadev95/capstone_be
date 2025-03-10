package epicode.it.capstone_be.entities.comune;

import epicode.it.capstone_be.entities.provincia.Provincia;
import epicode.it.capstone_be.entities.provincia.ProvinciaRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ComuneService {

    @Autowired
    private ComuneRepo comuneRepo;
    @Autowired
    private ProvinciaRepo provinciaRepo;

    public List<Comune> getComuneByProvincia(Long id_provincia) {
        Provincia provincia = provinciaRepo.findById(id_provincia).orElse(null);
        if(provincia == null) {
            throw new EntityNotFoundException("Provincia non trovata");
        }
        return comuneRepo.findByProvincia(provincia);
    }
}
