package epicode.it.capstone_be.auth;

import epicode.it.capstone_be.auth.requests_responses.AuthResponse;
import epicode.it.capstone_be.auth.requests_responses.LoginRequest;
import epicode.it.capstone_be.auth.requests_responses.RegisterJudgeRequest;
import epicode.it.capstone_be.auth.requests_responses.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AppUserService appUserService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        appUserService.registerUser(
                Set.of(Role.ROLE_USER),
                registerRequest
        );
        return ResponseEntity.ok("Registrazione avvenuta con successo");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        String token = appUserService.authenticateUser(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/registerJudge")
    public ResponseEntity<String> registerJudge(@RequestBody RegisterJudgeRequest registerRequest) {
        appUserService.registerJudge(
                Set.of(Role.ROLE_JUDGE),
                registerRequest
        );
        return ResponseEntity.ok("Registrazione avvenuta con successo");
    }
}
