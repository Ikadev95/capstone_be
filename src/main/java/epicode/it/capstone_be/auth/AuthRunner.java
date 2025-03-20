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
                adminRequest.setEmail("fegughi@gmail.com");
                adminRequest.setTelefono("3929182982");
                adminRequest.setData_di_nascita(LocalDate.of(1995, 4, 11));
                adminRequest.setPrivacy(true);

                IndirizzoRequest indirizzo = new IndirizzoRequest();
                indirizzo.setVia(faker.address().streetName());
                indirizzo.setCivico(faker.address().buildingNumber());
                indirizzo.setComune_id(2L);

                adminRequest.setIndirizzo(indirizzo);
                appUserService.registerUser(Set.of(Role.ROLE_ADMIN), adminRequest);
            }

        }
    }
}