package epicode.it.capstone_be.auth.requests_responses;

import lombok.Data;

@Data
public class AppUserRequest {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
