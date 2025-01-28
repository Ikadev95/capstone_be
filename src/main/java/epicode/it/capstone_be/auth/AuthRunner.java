package epicode.it.capstone_be.auth;

import epicode.it.capstone_be.auth.requests_responses.RegisterRequest;
import epicode.it.capstone_be.entities.comune.Comune;
import epicode.it.capstone_be.entities.comune.ComuneRepo;
import epicode.it.capstone_be.entities.indirizzo.IndirizzoRepo;
import epicode.it.capstone_be.entities.indirizzo.IndirizzoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Order(3)
@Component
public class AuthRunner implements ApplicationRunner {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IndirizzoRepo indirizzoRepo;

    @Autowired
    private ComuneRepo comuneRepo;

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
        request.setPrivacy(true);
        request.setTelefono("123456789");
        request.setData_di_nascita(LocalDate.of(1995, 4, 11));
        request.setComune_di_nascita_id(1L);

        Comune c = comuneRepo.findById(1L).get();

        IndirizzoRequest indirizzo = new IndirizzoRequest();
        indirizzo.setVia("Via trento");
        indirizzo.setCivico("12");
        indirizzo.setComune_id(1L);

        request.setIndirizzo(indirizzo);

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
        requestUser.setTelefono("133456589");
        requestUser.setData_di_nascita(LocalDate.of(1990, 12, 1));
        requestUser.setComune_di_nascita_id(2L);


        Comune c1 = comuneRepo.findById(1L).get();

        IndirizzoRequest indirizzo1 = new IndirizzoRequest();
        indirizzo1.setVia("Via roma");
        indirizzo1.setCivico("12");
        indirizzo1.setComune_id(1L);

        requestUser.setIndirizzo(indirizzo1);

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
        requestJudge.setComune_di_nascita_id(3L);
        requestJudge.setTelefono("123455739");
        requestJudge.setData_di_nascita(LocalDate.of(1970, 4, 21));


        Comune c2 = comuneRepo.findById(1L).get();

        IndirizzoRequest indirizzo2 = new IndirizzoRequest();

        indirizzo2.setVia("Corso re umberto");
        indirizzo2.setCivico("34");
        indirizzo2.setComune_id(1L);


        requestJudge.setIndirizzo(indirizzo2);

        if (judgeUser.isEmpty()) {
            appUserService.registerUser(
                    Set.of(Role.ROLE_JUDGE),
                    requestJudge

            );
        }
    }
}
