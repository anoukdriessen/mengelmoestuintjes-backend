package nl.mengelmoestuintjes.gardening.model.users.Authority;

import nl.mengelmoestuintjes.gardening.model.users.UserRole;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
@IdClass(AuthorityKey.class)
public class Authority implements Serializable {

    @Id
    @Column(nullable = false)
    private String username;

    @Id
    @Column(nullable = false)
    private UserRole role;

    public Authority() {}
    public Authority(String username, UserRole role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }
    public UserRole getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", this.username, this.role);
    }
}
