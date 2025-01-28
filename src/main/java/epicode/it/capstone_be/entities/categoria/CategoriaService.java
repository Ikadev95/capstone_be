package epicode.it.capstone_be.entities.categoria;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaService {

    private final CategoriaRepo categoriaRepo;

    public List<Categoria> findAllCategories() {return categoriaRepo.findAll();}

    public Categoria getCategoriaById(Long id) {
        return categoriaRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Categoria non trovata"));
    }

    public Categoria createCategoria(CategoriaRequest c) {
        Categoria categoria = new Categoria();
        BeanUtils.copyProperties(c, categoria);
        return categoriaRepo.save(categoria);
    }

    public void deleteCategoria(Long id) {
        categoriaRepo.deleteById(id);
    }
}
