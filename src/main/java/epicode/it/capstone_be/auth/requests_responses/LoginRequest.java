package epicode.it.capstone_be.auth.requests_responses;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
