package nl.mengelmoestuintjes.gardening.model;

import lombok.Data;
import nl.mengelmoestuintjes.gardening.controller.exceptions.BadRequestException;
import nl.mengelmoestuintjes.gardening.model.posts.Post;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @Column(nullable = false, unique = true)
    private String username; // TODO ISVALID USERNAME

    @Column(nullable = false, length = 80)
    private String password; // TODO ISVALID PASSWORD

    @Column(nullable = false)
    private boolean enabled = true;

    @Column(nullable = false, unique = true)
    private String email; // TODO ISVALID EMAIL

    private String level;
    private String xp;
    private String levelUpLimit;
    private String name; // TODO ISVALID NAME

    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private Province province;

    private LocalDate memberSince = LocalDate.now();
    private LocalDateTime lastActivity = LocalDateTime.now();

    @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "username",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<Authority> authorities = new ArrayList<>();

    @OneToMany(
            mappedBy = "author",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    private List<Post> posts = new ArrayList<>();

//    @OneToMany(
//            mappedBy = "owner",
//            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
//                    CascadeType.DETACH, CascadeType.REFRESH},
//            fetch = FetchType.LAZY
//    )
//    private List<Milestone> milestones = new ArrayList<>();

    @OneToMany(
            mappedBy = "owner",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    private List<Task> tasks = new ArrayList<>();

    //TODO ADD favorites (posts / plants / people)
    // private List<Favorite> favorites = new ArrayList<>()
    // methods: contains // add / remove

    //TODO ADD tasks
    // @OneToMany( mappedBy = "owner")
    // private List<Task> tasks = new ArrayList<>()
    // methods: contains // get GardenTasks // get ToDoTasks
    // add / remove

    //TODO ADD gardens
    // @ManyToMany
    // private List<Garden> gardens = new ArrayList<>()
    // methods: contains // add // remove

    //TODO ADD profile picture
    // @Lob
    // var profilePicture = ByteArray

    public void setDefaultValues(){
        this.setMemberSince(LocalDate.now());
        this.setLastActivity();
        this.setEnabled(true);

        this.setLevel(1);
        this.setXp("1000");
        this.setLevelUpLimit("2000");
    }

    public boolean birthdayIsToday() {
        return Objects.equals(this.birthday, LocalDate.now());
    }

    // AUTHORITHIES
    private boolean isPossibleAuthority(String toAdd) {
        List<String> possible = new ArrayList<>();

        possible.add("ROLE_USER");
        possible.add("ROLE_MODERATOR");
        possible.add("ROLE_DEVELOPER");
        possible.add("ROLE_ADMIN");

        for (String role : possible) {
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
        String toRemove = standarizeAuthorityString(authorityString);
        this.authorities.removeIf(authority -> authority.getAuthority().equalsIgnoreCase(toRemove));
    }
    public void setLastActivity() {
        this.lastActivity = LocalDateTime.now();
    }

    // LEVEL (XP / LIMIT)
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
    public String setUserXp (String xp){
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

    // POSTS
    public boolean hasPost(Post post) {
        for ( Post p : this.getPosts() ) {
            if ( p.equals(post) ) return true;
        }
        return false;
    }
    public void addPost(Post post){
        this.posts.add(post);
    }
    public void removePost(Post post){
        if (!this.hasPost(post)) {
            this.posts.remove(post);
        } else {
            throw new BadRequestException("post not found");
        }
    }

    // MILESTONES
    /**
    public boolean hasMilestone(Milestone milestone) {
        for (Milestone m : this.getMilestones() ) {
            if ( m.equals(milestone) ) return true;
        }
        return false;
    }
    public boolean isValidMilestone(Milestone milestone) {
        for (Milestones m : Milestones.values() ) {
            if ( m.equals(milestone.getMilestone()) ) return true;
        }
        return false;
    }
    public void addMilestone(Milestone milestone) {
        if (isValidMilestone(milestone)){
            this.milestones.add(milestone);
        } else {
            throw new InvalidException("milestone doesn't exist");
        }
    }
    public void removeMilestone(Milestone milestone) {
        if (!this.hasMilestone(milestone)) {
            this.milestones.remove(milestone);
        } else {
            throw new BadRequestException("milestone not found");
        }
    }**/
}
