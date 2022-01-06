package nl.mengelmoestuintjes.gardening.model.users;

import nl.mengelmoestuintjes.gardening.model.Milestone;
import nl.mengelmoestuintjes.gardening.model.garden.Garden;
import nl.mengelmoestuintjes.gardening.model.plants.Plant;
import nl.mengelmoestuintjes.gardening.model.posts.Post;
import nl.mengelmoestuintjes.gardening.model.tasks.Task;

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

    @Column(nullable = false)
    private boolean enabled = true;

    private String email;

    @OneToMany( targetEntity = Authority.class,
                mappedBy = "username",
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.EAGER )
    private List<Authority> authorities = new ArrayList<>();

//    @Column(nullable = true)
//    private int lvl = 1;
//    @Column(nullable = true)
//    private long xp = 0;
//    @Column(nullable = true)
//    private long levelUpLimit = 1000;

    private String name;
    private LocalDate birthday;
    private Province province;

// TODO profile picture
//    @Lob
//    @Column(name="profile_picture")
//    var profilePicture: ByteArray

    private LocalDateTime memberSince;
    private LocalDateTime lastActivity;

    @OneToMany( mappedBy = "owner" )
     private List<Milestone> milestones = new ArrayList<>();

    @OneToMany( mappedBy = "owner" )
    private List<Post> posts = new ArrayList<>();

    @OneToMany( mappedBy = "owner" )
     private List<Post> favoritePosts =  new ArrayList<>();

    @OneToMany( mappedBy = "owner" )
     private List<Task> tasks = new ArrayList<>();

    @OneToMany( mappedBy = "owner" )
     private List<Garden> gardens = new ArrayList<>();

    @OneToMany( mappedBy = "owner" )
     private List<Plant> favoritePlants = new ArrayList<>();

    // TODO ManyToMany
    // private List<User> friends;

    public User(){}
    public User(String username, String password, boolean enabled){
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }
//    public User(String username, String password, boolean enabled, String email, List<Authority> authorities, int lvl, long xp, long levelUpLimit, String name, LocalDate birthday, Province province, LocalDateTime memberSince, List<Milestone> milestones, List<Post> posts, List<Post> favoritePosts, List<Task> tasks, List<Garden> gardens, List<Plant> favoritePlants) {
    public User(String username, String password, boolean enabled, String email, List<Authority> authorities, String name, LocalDate birthday, Province province, LocalDateTime memberSince, List<Milestone> milestones, List<Post> posts, List<Post> favoritePosts, List<Task> tasks, List<Garden> gardens, List<Plant> favoritePlants) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.email = email;
        this.authorities = authorities;
//        this.lvl = lvl;
//        this.xp = xp;
//        this.levelUpLimit = levelUpLimit;
        this.name = name;
        this.birthday = birthday;
        this.province = province;
        this.memberSince = memberSince;
        setLastActivity();
        this.milestones = milestones;
        this.posts = posts;
        this.favoritePosts = favoritePosts;
        this.tasks = tasks;
        this.gardens = gardens;
        this.favoritePlants = favoritePlants;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public String getEmail() {
        return email;
    }
//    public int getLvl() {
//        return lvl;
//    }
//    public long getXp() {
//        return xp;
//    }
//    public long getLevelUpLimit() {
//        return levelUpLimit;
//    }
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
    public List<Milestone> getMilestones() {
        return milestones;
    }
    public List<Post> getPosts() {
        return posts;
    }
    public List<Post> getFavoritePosts() {
        return favoritePosts;
    }
    public List<Task> getTasks() {
        return tasks;
    }
    public List<Garden> getGardens() {
        return gardens;
    }
    public List<Plant> getFavoritePlants() {
        return favoritePlants;
    }
    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public void setEmail(String email) {
        this.email = email;
    }
//    public void setLvl(int lvl) {
//        this.lvl = lvl;
//    }
//    public void setXp(long xp) {
//        this.xp = xp;
//    }
//    public void setLevelUpLimit(long levelUpLimit) {
//        this.levelUpLimit = levelUpLimit;
//    }
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
    public void setLastActivity() {
        this.lastActivity = LocalDateTime.now();
    }
    public void setMilestones(List<Milestone> milestones) {
        this.milestones = milestones;
    }
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    public void setFavoritePosts(List<Post> favoritePosts) {
        this.favoritePosts = favoritePosts;
    }
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    public void setGardens(List<Garden> gardens) {
        this.gardens = gardens;
    }
    public void setFavoritePlants(List<Plant> favoritePlants) {
        this.favoritePlants = favoritePlants;
    }
    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    // TODO add / remove milestone
    // TODO add / remove favoriteposts
    // TODO add / remove tasks
    // TODO add / remove gardens
    // TODO add / remove favoriteplants

    public boolean hasAuthority(String s) {
        return authorities.stream().anyMatch( e -> e.getAuthority().equals(s));
    }
    public void addAuthority(String authorityString) {
        String s = standarizeAuthorityString(authorityString);
        if (!hasAuthority(s)) {
            this.authorities.add(new Authority(this.username, s));
        }
    }
    public void removeAuthority(String authorityString) {
        String s = standarizeAuthorityString(authorityString);
        this.authorities.removeIf(authority -> authority.getAuthority().equals(s));
    }
    private static String standarizeAuthorityString(String authorityString) {
        String s = authorityString.toUpperCase();
        if (!s.startsWith("ROLE_")) {
            s = "ROLE_" + s;
        }
        return s;
    }

    public LocalDateTime getCurrentDate() {
        return LocalDateTime.now();
    }
}
