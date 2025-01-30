package epicode.it.capstone_be.entities.provincia;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProvinciaService {
    private final ProvinciaRepo provinciaRepo;

    public List<Provincia> getAll() { return provinciaRepo.findAll();}
}
