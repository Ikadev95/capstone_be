package epicode.it.capstone_be.auth;

import epicode.it.capstone_be.auth.jwt.JwtTokenUtil;
import epicode.it.capstone_be.auth.requests_responses.RegisterRequest;
import epicode.it.capstone_be.entities.comune.Comune;
import epicode.it.capstone_be.entities.comune.ComuneRepo;
import epicode.it.capstone_be.entities.indirizzo.Indirizzo;
import epicode.it.capstone_be.entities.provincia.Provincia;
import epicode.it.capstone_be.entities.provincia.ProvinciaRepo;
import epicode.it.capstone_be.entities.utente.Utente;
import epicode.it.capstone_be.entities.utente.UtenteRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private ProvinciaRepo provinciaRepo;

    @Autowired
    private ComuneRepo comuneRepo;

    @Transactional
    public AppUser registerUser(Set<Role> roles, RegisterRequest registerRequest) {
        if (appUserRepository.existsByUsername(registerRequest.getUsername())) {
            throw new EntityExistsException("Username già in uso");
        }

        AppUser appUser = new AppUser();
        appUser.setUsername(registerRequest.getUsername());
        appUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        appUser.setRoles(roles);


        Utente utente = new Utente();
        utente.setNome(registerRequest.getNome());
        utente.setCognome(registerRequest.getCognome());
        utente.setEmail(registerRequest.getEmail());
        utente.setTelefono(registerRequest.getTelefono());
        utente.setData_di_nascita(registerRequest.getData_di_nascita());
        utente.setPrivacy(registerRequest.isPrivacy());

        utente.setAppUser(appUser);

        appUser = appUserRepository.save(appUser);

        utenteRepository.save(utente);

        return appUser;

    }

    public Optional<AppUser> findByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    public String authenticateUser(String username, String password)  {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            throw new SecurityException("Credenziali non valide", e);
        }
    }


    public AppUser loadUserByUsername(String username)  {
        AppUser appUser = appUserRepository.findByUsername(username)
            .orElseThrow(() -> new EntityNotFoundException("Utente non trovato con username: " + username));


        return appUser;
    }
}
