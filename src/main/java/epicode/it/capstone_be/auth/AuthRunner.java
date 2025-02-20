package epicode.it.capstone_be.auth;

import epicode.it.capstone_be.auth.requests_responses.RegisterJudgeRequest;
import epicode.it.capstone_be.auth.requests_responses.RegisterRequest;
import epicode.it.capstone_be.entities.comune.Comune;
import epicode.it.capstone_be.entities.comune.ComuneRepo;
import epicode.it.capstone_be.entities.indirizzo.IndirizzoRepo;
import epicode.it.capstone_be.entities.indirizzo.IndirizzoRequest;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Order(4)
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

    @Autowired
    private AppUserRepository appUserRepository;

    private Faker faker = new Faker();

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (appUserRepository.count() == 0) {

            // Creazione dell'utente admin
            Optional<AppUser> adminUser = appUserService.findByUsername("admin");
            if (adminUser.isEmpty()) {
                RegisterRequest adminRequest = new RegisterRequest();
                adminRequest.setCognome("Guglielmo");
                adminRequest.setNome("Federica");
                adminRequest.setUsername("admin");
                adminRequest.setPassword("adminpwd");
                adminRequest.setEmail(faker.internet().emailAddress());
                adminRequest.setTelefono(faker.phoneNumber().phoneNumber());
                adminRequest.setData_di_nascita(LocalDate.of(1995, 4, 11));
                adminRequest.setPrivacy(true);

                IndirizzoRequest indirizzo = new IndirizzoRequest();
                indirizzo.setVia(faker.address().streetName());
                indirizzo.setCivico(faker.address().buildingNumber());
                indirizzo.setComune_id(2L);

                adminRequest.setIndirizzo(indirizzo);
                appUserService.registerUser(Set.of(Role.ROLE_ADMIN), adminRequest);
            }

            // Creazione dei 6 giudici
            String[] categorie = {
                    "poesia in ITALIANO a tema fisso",
                    "poesia in ITALIANO a tema libero",
                    "poesia in PIEMONTESE a tema fisso",
                    "poesia in PIEMONTESE a tema libero",
                    "fotografia a tema fisso",
                    "fotografia a tema libero"
            };

            for (int i = 0; i < categorie.length; i++) {
                String giudiceUsername = "judge" + (i + 1);
                Optional<AppUser> judgeUser = appUserService.findByUsername(giudiceUsername);
                if (judgeUser.isEmpty()) {
                    RegisterJudgeRequest judgeRequest = new RegisterJudgeRequest();
                    judgeRequest.setCognome(faker.name().lastName());
                    judgeRequest.setNome(faker.name().firstName());
                    judgeRequest.setUsername(giudiceUsername);
                    judgeRequest.setPassword("judge" + (i + 1) + "pwd");
                    judgeRequest.setEmail(faker.internet().emailAddress());

                    appUserService.registerJudge(Set.of(Role.ROLE_JUDGE), judgeRequest);
                }
            }

            // Creazione degli utenti standard
            for (int i = 0; i < 9; i++) {
                String userUsername = "user" + (i + 1);
                Optional<AppUser> normalUser = appUserService.findByUsername(userUsername);
                if (normalUser.isEmpty()) {
                    RegisterRequest userRequest = new RegisterRequest();
                    userRequest.setCognome(faker.name().lastName());
                    userRequest.setNome(faker.name().firstName());
                    userRequest.setUsername(userUsername);
                    userRequest.setPassword("user" + (i + 1) + "pwd");
                    userRequest.setEmail(faker.internet().emailAddress());
                    userRequest.setTelefono(faker.phoneNumber().phoneNumber());
                    userRequest.setData_di_nascita(LocalDate.of(1990 + i, 1, 1));
                    userRequest.setPrivacy(true);

                    Comune comune = comuneRepo.findById(1L).get();
                    IndirizzoRequest indirizzo = new IndirizzoRequest();
                    indirizzo.setVia(faker.address().streetName());
                    indirizzo.setCivico(faker.address().buildingNumber());
                    indirizzo.setComune_id(3L);

                    userRequest.setIndirizzo(indirizzo);
                    appUserService.registerUser(Set.of(Role.ROLE_USER), userRequest);
                }
            }
        }
    }
}