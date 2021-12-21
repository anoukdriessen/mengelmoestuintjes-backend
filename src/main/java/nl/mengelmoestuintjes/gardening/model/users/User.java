package nl.mengelmoestuintjes.gardening.model.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import nl.mengelmoestuintjes.gardening.model.Milestone;
import nl.mengelmoestuintjes.gardening.model.garden.Garden;
import nl.mengelmoestuintjes.gardening.model.posts.Post;
import nl.mengelmoestuintjes.gardening.model.tasks.Task;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id",nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

//    TODO implement email
//    @Column(nullable = false)
//    private String email;

    @Column(columnDefinition = "integer default 0")
    private int lvl;
    @Column(columnDefinition = "bigint default 0")
    private long xp;
    private String name;
    private LocalDate birthday;
    private Province province;

// TODO profile picture
//    @Lob
//    @Column(name="profile_picture")
//    var profilePicture: ByteArray

    private LocalDateTime memberSince;
    private LocalDateTime lastActivity;

    private UserRole role;

    @OneToMany( mappedBy = "owner" )
     private List<Milestone> milestones;

    @OneToMany( mappedBy = "owner" )
    private List<Post> posts = new ArrayList<>();

    @OneToMany( mappedBy = "owner")
     private List<Post> favoritePosts;

    @OneToMany( mappedBy = "owner" )
     private List<Task> tasks;


    @OneToMany( mappedBy = "owner",  fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true )
     private List<Garden> gardens;

// TODO add relations
    // TODO ManyToMany
    // private List<User> friends;
    // TODO implement Plant
    // private List<Plant> favoritePlants;

    @JsonIgnore
    @Column(columnDefinition = "bigint default 0")
    private long levelUpLimit; // starting at 1000 xp

    public User(){};
    public User(int lvl, long xp, String username, String password, String name, LocalDate birthday, Province province, LocalDateTime memberSince, LocalDateTime lastActivity, UserRole role, long levelUpLimit) {
        this.lvl = lvl;
        this.xp = xp;
        this.username = username;
//        this.email = email;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.province = province;
        this.memberSince = memberSince;
        this.lastActivity = lastActivity;
        this.role = role;
        this.levelUpLimit = levelUpLimit;
    }

    /**
     * Custom Constructor, the user is created with level 1 and 0 xp the first limit level is 1000 xp.
     * The date for memberSince and lastActivity is set to current Date and Time
     * @param username the chosen username
     *                 // TODO EMAIL
     * @param password the chosen password TODO ENCRYPTION
     * @param role the role for the user
     */
    public User( String username, String password, UserRole role ) {
        this.lvl = 1;                           // starting at level 1
        this.xp = 0;                            // starting at 0 xp
        this.levelUpLimit = 1000;               // starting at 1000 xp

        // today
        this.memberSince = getCurrentDate();
        this.lastActivity = getCurrentDate();

        this.username = username;
//        this.email = email;
        this.password = password;

//      // set to null, will be set on create Profile
        createProfile(null, null, null);

        this.role = role;
    }

    public int getLvl() {
        return lvl;
    }
    public long getXp() {
        return xp;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public LocalDate getBirthday() {
        return birthday;
    }
    public Province getProvince() {
        return province;
    }
    public LocalDateTime getMemberSince() {
        return memberSince;
    }
    public LocalDateTime getLastActivity() {
        return lastActivity;
    }
    public UserRole getRole() {
        return role;
    }
    public long getLevelUpLimit() {
        return levelUpLimit;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
    public void setXp(long xp) {
        this.xp = xp;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    public void setProvince(Province province) {
        this.province = province;
    }
    public void setMemberSince(LocalDateTime memberSince) {
        this.memberSince = memberSince;
    }
    public void setLastActivity(LocalDateTime lastActivity) {
        this.lastActivity = lastActivity;
    }
    public void setRole(UserRole role) {
        this.role = role;
    }
    public void setLevelUpLimit(long levelUpLimit) {
        this.levelUpLimit = levelUpLimit;
    }

    @Override
    public String toString() {
        return String.format("%d ) [ %d - %d ] - %s \n %s / %s / %s \n %s / %s \n %s ",
                this.lvl, this.xp, this.username,
                this.name, this.birthday, this.province,
                this.memberSince, this.lastActivity,
                this.role);
    }

    /**
     * Method to run when creating a new profile
     * @param name name of user
     * @param birthday birthday of user
     * @param province province that is selected
     */
    public void createProfile( String name, LocalDate birthday, Province province ) {
        this.setName(name);
        this.setBirthday(birthday);
        this.setProvince(province);
    }

    /**
     * Method to return the currentDate
     * @return LocalDateTime
     */
    public LocalDateTime getCurrentDate() {
        return LocalDateTime.now();
    }
}
