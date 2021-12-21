package nl.mengelmoestuintjes.gardening.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AuthenticationRequestDto {

    @NotEmpty
    private String username;

    @Size(min = 8)
    private String password;

    public AuthenticationRequestDto() {}
    public AuthenticationRequestDto( String username, String password ) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
