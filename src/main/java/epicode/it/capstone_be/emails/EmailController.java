package epicode.it.capstone_be.emails;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/{isHtml}")
    public ResponseEntity<String> sendHtmlEmail(@RequestBody EmailRequest request, @RequestParam boolean isHtml) {
        if (isHtml) {
            return new ResponseEntity<>(emailService.sendEmail(request), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(emailService.sendEmail(request), HttpStatus.CREATED);
        }
    }
}
