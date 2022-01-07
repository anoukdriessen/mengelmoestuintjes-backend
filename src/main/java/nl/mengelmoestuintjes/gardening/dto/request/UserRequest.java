package nl.mengelmoestuintjes.gardening.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.mengelmoestuintjes.gardening.model.users.Province;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String username;
    private String password;
    private boolean enabled;
    private List<String> authorities;
    private String email;
    private String level;
    private String xp;
    private String levelUpLimit;
    private String name;
    private LocalDate birthday;
    private Province province;
    private LocalDate memberSince;
    private LocalDateTime lastActivity;
}
