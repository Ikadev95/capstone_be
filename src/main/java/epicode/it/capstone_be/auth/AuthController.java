package epicode.it.capstone_be.auth;

import epicode.it.capstone_be.auth.requests_responses.*;
import epicode.it.capstone_be.emails.EmailRequest;
import epicode.it.capstone_be.emails.EmailService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AppUserService appUserService;
    @Autowired
    private  EmailService emailService;
    @Autowired
    private  PasswordResetTokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequest registerRequest) {
        appUserService.registerUser(
                Set.of(Role.ROLE_USER),
                registerRequest
        );
        Map<String, String> response = new HashMap<>();
        response.put("message", "Registrazione avvenuta con successo");
        return ResponseEntity.ok(response);
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
    public ResponseEntity<Map<String, String>> registerJudge(@RequestBody RegisterJudgeRequest registerRequest) {
        appUserService.registerJudge(
                Set.of(Role.ROLE_JUDGE),
                registerRequest
        );
        Map<String, String> response = new HashMap<>();
        response.put("message", "Registrazione avvenuta con successo");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Map<String, String>> requestPasswordReset(@RequestBody @Valid PasswordResetRequest request) {
        String token = tokenService.createPasswordResetToken(request.getEmail());
        String resetLink = "https://concorsocircoloairali.it/auth/reset?token=" + token + "&email=" + request.getEmail();

        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setTo(request.getEmail());
        emailRequest.setSubject("Reset della tua password");
        emailRequest.setBody(
                "<h2>Reset della tua password</h2>" +
                        "<p>Clicca sul seguente link per reimpostare la tua password:</p>" +
                        "<p><a href='" + resetLink + "' style='color: blue; font-weight: bold;'>Reimposta password</a></p>" +
                        "<p>Se non hai richiesto il reset, ignora questa email.</p>"
        );

        emailService.sendEmailHtml(emailRequest);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Email di reset inviata con successo!");
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping("/reset-password/confirm")
    public ResponseEntity<String> resetPassword(@RequestBody @Valid PasswordResetConfirmRequest request) {
        if (!tokenService.isValidToken(request.getToken())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token non valido o scaduto.");
        }

        appUserService.updatePassword(request.getEmail(), request.getNewPassword());
        tokenService.deleteToken(request.getToken());

        return ResponseEntity.ok("Password aggiornata con successo.");
    }
}
