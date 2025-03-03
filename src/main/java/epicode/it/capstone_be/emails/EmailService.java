package epicode.it.capstone_be.emails;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class EmailService {
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    public String sendEmail(@Valid EmailRequest request) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(request.getTo());
        message.setSubject(request.getSubject());
        message.setText(request.getBody());
        message.setFrom(from);

        mailSender.send(message);
        return "Mail inviata correttamente a: " + request.getTo();
    }

    public String sendEmailHtml(@Valid EmailRequest request) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setTo(request.getTo());
            helper.setSubject(request.getSubject());
            helper.setText(request.getBody(), true);
            helper.setFrom(from);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return "Mail inviata correttamente a:" + request.getTo();
    }
}
