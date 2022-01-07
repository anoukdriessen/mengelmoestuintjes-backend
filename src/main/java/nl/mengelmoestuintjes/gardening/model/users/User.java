package nl.mengelmoestuintjes.gardening.model.users;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, length = 80)
    private String password;

    @Column(nullable = false)
    private boolean enabled = true;

    //TODO check has authority / is authority before add authority
    // remove authority
    @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "username",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private List<Authority> authorities = new ArrayList<>();

    @Column(nullable = false, unique = true)
    private String email;

    private String level;
    private String xp;
    private String levelUpLimit;
    // TODO update method for level / xp / levelUpLimit
    private String name;

    private LocalDate birthday;
    // TODO remove birthday (cannot be updated)

    @Enumerated(EnumType.STRING)
    private Province province;

    private LocalDate memberSince = LocalDate.now();
    private LocalDateTime lastActivity = LocalDateTime.now();

    //TODO add milestones
    // @OneToMany( mappedBy = "owner" )
    // private List<Milestones> milestones = new ArrayList<>();
    // methods: has / add / remove

    //TODO add favorites (posts / plants / people)
    // private List<Favorite> favorites = new ArrayList<>()
    // methods: contains // add / remove

    //TODO add tasks
    // @OneToMany( mappedBy = "owner")
    // private List<Task> tasks = new ArrayList<>()
    // methods: contains // get GardenTasks // get ToDoTasks
    // add / remove

    //TODO add gardens
    // @ManyToMany
    // private List<Garden> gardens = new ArrayList<>()
    // methods: contains // add // remove

    //TODO add posts
    // @OneToMany( mappedBy = "owner" )
    // private List<Post> posts = new ArrayList<>()
    // methos: add / remove

    //TODO add profile picture
    // @Lob
    // var profilePicture = ByteArray

    private static String standarizeAuthorityString( String authorityString ) {
        String s = authorityString.toUpperCase();
        if (!s.startsWith("ROLE_")) {
            s = "ROLE_" + s;
        }
        return s;
    }
    public void addAuthority(Authority authority) {
        String authorityString = authority.getAuthority();
        authorityString = standarizeAuthorityString(authorityString);
        this.authorities.add( new Authority( this.username, authorityString ) );
    }
    public void addAuthority(String authorityString) {
        authorityString = standarizeAuthorityString(authorityString);
        this.authorities.add( new Authority( this.username, authorityString ));
    }
    public void removeAuthority(Authority authority) {
        this.authorities.remove(authority);
    }
    public void removeAuthority(String authorityString) {
        this.authorities.removeIf(authority -> authority.getAuthority().equalsIgnoreCase(authorityString));
    }
    public void setLastActivity() {
        this.lastActivity = LocalDateTime.now();
    }

    // TODO XP WORDT NIET GEUPDATE
}
