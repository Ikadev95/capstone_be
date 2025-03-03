package epicode.it.capstone_be.emails;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmailRequest {
    @Email(message = "indirizzo mail non valido")
    private String replyTo;

    @Email(message = "indirizzo mail non valido")
    private String to;

    @Email(message = "indirizzo mail non valido")
    private String cc;

    @NotBlank(message = "il soggetto è richiesto")
    private String subject;

    @NotBlank(message = "il corpo della mail è richiesto")
    private String body;
}
