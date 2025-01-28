package epicode.it.capstone_be.entities.categoria;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CategoriaMapper {
    private ModelMapper modelMapper = new ModelMapper();

    public CategoriaResponse mapCategoria(Categoria categoria) {
        CategoriaResponse categoriaResponse = modelMapper.map(categoria, CategoriaResponse.class);
        return categoriaResponse;
    }

    public List<CategoriaResponse> mapCategoriaResponseList(List<Categoria> categorie) {
        return categorie.stream().map(this::mapCategoria).toList();
    }
}
