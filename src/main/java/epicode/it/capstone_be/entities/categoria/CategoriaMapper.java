package epicode.it.capstone_be.entities.categoria;

import epicode.it.capstone_be.auth.AppUserMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoriaMapper {

    private final AppUserMapper appUserMapper;
    private final ModelMapper modelMapper;

    public CategoriaMapper(AppUserMapper appUserMapper, ModelMapper modelMapper) {
        this.appUserMapper = appUserMapper;
        this.modelMapper = modelMapper;
    }

    public CategoriaResponse mapCategoria(Categoria categoria) {
        CategoriaResponse categoriaResponse = modelMapper.map(categoria, CategoriaResponse.class);
        categoriaResponse.setGiudici(appUserMapper.mapAppUserResponseList(categoria.getGiudici()));
        return categoriaResponse;
    }

    public List<CategoriaResponse> mapCategoriaResponseList(List<Categoria> categorie) {
        return categorie.stream().map(this::mapCategoria).toList();
    }
}