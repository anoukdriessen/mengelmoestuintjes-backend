package nl.mengelmoestuintjes.gardening.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.mengelmoestuintjes.gardening.controller.exceptions.BadRequestException;
import nl.mengelmoestuintjes.gardening.model.Post;
import nl.mengelmoestuintjes.gardening.model.Province;
import nl.mengelmoestuintjes.gardening.model.Task;
import nl.mengelmoestuintjes.gardening.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String username;
    private String password;
    private boolean enabled;
    private String email;
    private String level;
    private String xp;
    private String levelUpLimit;
    private String name;
    private LocalDate birthday;
    private Province province;
    private LocalDate memberSince;
    private LocalDateTime lastActivity;
    private List<String> authorities;
    private ArrayList<Post> posts = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();
    private byte[] profileImg;

    public boolean isEmpty(String item){
        return item == null || item.isEmpty() || item.isBlank();
    }
    public void setUsername(String username) {
        if (isEmpty(username)) { throw new BadRequestException("Username missing or invalid");
        } else { this.username = username; }
    }

    public void setPassword(String password) {
        if (isEmpty(password)) { throw new BadRequestException("Password missing or invalid");
        } else { this.password = password; }
    }

    public void setEmail(String email) {
        if (isEmpty(email)) { throw new BadRequestException("Email missing or invalid");
        } else { this.email = email; }
    }

    public User toUser() {
        User u = new User();
        u.setUsername( this.getUsername() );
        u.setPassword( this.getPassword() );
        u.setEmail( this.getEmail() );
        return u;
    }
}
