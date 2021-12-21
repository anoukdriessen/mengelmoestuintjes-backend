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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(nullable = false)
    private String email;

    @OneToMany( targetEntity = Authority.class,
                mappedBy = "username",
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.EAGER )
    private Set<Authority> authorities = new HashSet<>();

    private int lvl = 1;
    private long xp = 0;
    private long levelUpLimit = 1000;

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
    public User(String username, String password, boolean enabled, String email, Set<Authority> authorities, int lvl, long xp, long levelUpLimit, String name, LocalDate birthday, Province province, LocalDateTime memberSince, LocalDateTime lastActivity, List<Milestone> milestones, List<Post> posts, List<Post> favoritePosts, List<Task> tasks, List<Garden> gardens, List<Plant> favoritePlants) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.email = email;
        this.authorities = authorities;
        this.lvl = lvl;
        this.xp = xp;
        this.levelUpLimit = levelUpLimit;
        this.name = name;
        this.birthday = birthday;
        this.province = province;
        this.memberSince = memberSince;
        this.lastActivity = lastActivity;
        this.milestones = milestones;
        this.posts = posts;
        this.favoritePosts = favoritePosts;
        this.tasks = tasks;
        this.gardens = gardens;
        this.favoritePlants = favoritePlants;
    }
    // TODO CUSTOM CONSTRUCTOR

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
    public int getLvl() {
        return lvl;
    }
    public long getXp() {
        return xp;
    }
    public long getLevelUpLimit() {
        return levelUpLimit;
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
    public Set<Authority> getAuthorities() {
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
    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
    public void setXp(long xp) {
        this.xp = xp;
    }
    public void setLevelUpLimit(long levelUpLimit) {
        this.levelUpLimit = levelUpLimit;
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
    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }
    public void addAuthority(String authorityString) {
        this.authorities.add(new Authority(this.username, authorityString));
    }
    public void removeAuthority(Authority authority) {
        this.authorities.remove(authority);
    }
    public void removeAuthority(String authorityString) {
        this.authorities.removeIf(authority -> authority.getAuthority().equalsIgnoreCase(authorityString));
    }

    // TODO add / remove milestone
    // TODO add / remove favoriteposts
    // TODO add / remove tasks
    // TODO add / remove gardens
    // TODO add / remove favoriteplants


    /**
     * Method to return the currentDate
     * @return LocalDateTime
     */
    public LocalDateTime getCurrentDate() {
        return LocalDateTime.now();
    }
}
