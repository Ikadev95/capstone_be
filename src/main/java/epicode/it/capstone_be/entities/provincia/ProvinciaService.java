package epicode.it.capstone_be.entities.provincia;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProvinciaService {

    @Autowired
    private ProvinciaRepo provinciaRepo;

    public List<Provincia> getAll() { return provinciaRepo.findAll();}
}
