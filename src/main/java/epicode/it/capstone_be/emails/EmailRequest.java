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

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
