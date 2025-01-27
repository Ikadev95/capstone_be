package epicode.it.capstone_be.auth;

import epicode.it.capstone_be.auth.requests_responses.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class AuthRunner implements ApplicationRunner {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Creazione dell'utente admin se non esiste
        Optional<AppUser> adminUser = appUserService.findByUsername("admin");
        RegisterRequest request = new RegisterRequest();
        request.setCognome("Admin");
        request.setNome("Admin");
        request.setUsername("admin");
        request.setPassword("adminpwd");
        request.setEmail("admin@example.com");
        if (adminUser.isEmpty()) {
            appUserService.registerUser(
                    Set.of(Role.ROLE_ADMIN),
                    request

            );
        }

        // Creazione dell'utente user se non esiste
        Optional<AppUser> normalUser = appUserService.findByUsername("user");
        RegisterRequest requestUser = new RegisterRequest();
        requestUser.setCognome("User");
        requestUser.setNome("User");
        requestUser.setUsername("user");
        requestUser.setPassword("userpwd");
        requestUser.setEmail("user@example.com");
        if (normalUser.isEmpty()) {
            appUserService.registerUser(
                    Set.of(Role.ROLE_USER),
                    requestUser

            );
        }

        // Creazione dell'utente judge se non esiste
        Optional<AppUser> judgeUser = appUserService.findByUsername("judge");
        RegisterRequest requestJudge = new RegisterRequest();
        requestJudge.setCognome("Judge");
        requestJudge.setNome("Judge");
        requestJudge.setUsername("judge");
        requestJudge.setPassword("judgepwd");
        requestJudge.setEmail("judge@example.com");
        if (judgeUser.isEmpty()) {
            appUserService.registerUser(
                    Set.of(Role.ROLE_JUDGE),
                    requestJudge

            );
        }
    }
}
