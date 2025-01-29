package epicode.it.capstone_be.auth;

import epicode.it.capstone_be.auth.requests_responses.AppUserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppUserMapper {

    private final ModelMapper modelMapper;

    // Inietta il ModelMapper tramite il costruttore
    public AppUserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AppUserRequest mapAppUser(AppUser categoria) {
        return modelMapper.map(categoria, AppUserRequest.class);
    }

    public List<AppUserRequest> mapAppUserResponseList(List<AppUser> categorie) {
        return categorie.stream().map(this::mapAppUser).toList();
    }
}