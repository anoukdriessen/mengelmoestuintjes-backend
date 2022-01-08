package nl.mengelmoestuintjes.gardening.model.users;

import lombok.Data;
import nl.mengelmoestuintjes.gardening.controller.exceptions.BadRequestException;

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

    @Transient
    private List<String> possibleAuthorities = new ArrayList<>();

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

    private boolean isPossibleAuthority(String toAdd){
        this.possibleAuthorities.add("ROLE_USER");
        this.possibleAuthorities.add("ROLE_MODERATOR");
        this.possibleAuthorities.add("ROLE_DEVELOPER");
        this.possibleAuthorities.add("ROLE_ADMIN");

        for (String role : possibleAuthorities) {
            if (role.equals( toAdd )) {
                return true;
            }
        }
        return false;
    }
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
        if ( isPossibleAuthority( authorityString )) {
            this.authorities.add(new Authority(this.username, authorityString));
        } else {
            throw new BadRequestException( "authority is not possible authority" );
        }
    }
    public void addAuthority(String authorityString) {
        authorityString = standarizeAuthorityString(authorityString);
        if ( isPossibleAuthority( authorityString )) {
            this.authorities.add( new Authority( this.username, authorityString ));
        } else {
            throw new BadRequestException( "authority is not possible authority" );
        }
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

    public long parseLong(String toParse) {
        return Long.parseLong(toParse);
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public void setLevel(long level) {
        this.level = "" + level;
    }
    public void setLevelUpLimit(String limit) {
        this.levelUpLimit = limit;
    }
    public void setLevelUpLimit(long limit) {
        this.levelUpLimit = "" + limit;
    }
    public void checkAndSetMax(long current) {
        if (current == 99) {
            this.setLevel("MAX");
            this.setLevelUpLimit("MAX");
        }
    }
    public void levelUp() {
        long level = parseLong(this.level);
        // user is already 99
        checkAndSetMax(level);
        level++;
        // user has become 99
        checkAndSetMax(level);
        this.setLevel(level);
    }
    public String setXp (String xp){
        String out = this.xp + " + " + xp + " = ";
        if (!this.xp.equals("MAX")) {
            Long current = parseLong(this.xp);
            Long toAdd = parseLong(xp);
            this.xp = "" + ( current + toAdd );
        }
        out += this.xp;

        // check and set level up limit
        long newXP = parseLong(this.xp);
        long limit = parseLong(this.levelUpLimit);
        if (newXP >= limit) {
            // level up limit behaald, verhoog limit
            limit = limit + ( limit / 3 );
            setLevelUpLimit( limit );
            this.levelUp();
            out += " user has leveled up ";
        }
        return out;
    }
}
