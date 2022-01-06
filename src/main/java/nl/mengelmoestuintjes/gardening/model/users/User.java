package nl.mengelmoestuintjes.gardening.model.users;

import lombok.Data;
import nl.mengelmoestuintjes.gardening.exceptions.NotAllowedException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @Column(name = "id",nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean enabled = true;

    @OneToMany( targetEntity = Authority.class,
                mappedBy = "username",
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.EAGER )
    private List<Authority> authorities = new ArrayList<>();

    public User() {}
    public User(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }
    public User(String username, String password, boolean enabled, List<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public boolean hasAuthority(String s ) {
        for ( Authority auth : authorities ) {
            String toFind = standarizeAuthorityString(s);
            if ( auth.getAuthority().equalsIgnoreCase( toFind ) ) return true;
        }
        return false; // not found
    }

    public void addAuthority( Authority authority ) {
        if ( !hasAuthority( authority.getAuthority() )) {
            this.authorities.add( authority );
        } else { throw new NotAllowedException("user already has authority"); }
    }
    public void addAuthority( String authorityString ) {
        String s = standarizeAuthorityString(authorityString);
        if (!hasAuthority(s)) {
            this.authorities.add(new Authority(this.username, s));
        }
    }

    public void removeAuthority(String authorityString) {
        String s = standarizeAuthorityString(authorityString);
        this.authorities.removeIf(authority -> authority.getAuthority().equals(s));
    }

    private static String standarizeAuthorityString( String authorityString ) {
        String s = authorityString.toUpperCase();
        if (!s.startsWith("ROLE_")) {
            s = "ROLE_" + s;
        }
        return s;
    }
}
