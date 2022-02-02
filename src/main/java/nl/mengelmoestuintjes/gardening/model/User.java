package nl.mengelmoestuintjes.gardening.model;

import nl.mengelmoestuintjes.gardening.controller.exceptions.BadRequestException;
import nl.mengelmoestuintjes.gardening.dto.response.UserResponse;
import nl.mengelmoestuintjes.gardening.model.garden.Garden;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false, length = 80)
    private String password;

    @Column(nullable = false)
    private boolean enabled = true;

    private String level;
    private String xp;
    private String levelUpLimit;

    private String name;
    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] profileImg;

    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private Province province;

    private LocalDate memberSince = LocalDate.now();
    private LocalDate lastActivity = LocalDate.now();

    @JsonIgnore
    @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "username",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<Authority> authorities = new ArrayList<>();

    @JsonIgnore
    @OneToMany(
            mappedBy = "author",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    private List<Post> posts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(
            mappedBy = "owner",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    private List<Task> tasks = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH}
    )
    @JoinTable(
            name = "gardens_users",
            joinColumns=@JoinColumn(name = "garden_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<Garden> gardens;

    public long parseLong(String toParse) {
        return Long.parseLong(toParse);
    }
    public boolean birthdayIsToday() {
        if (this.birthday.getMonthValue() == LocalDate.now().getMonthValue()) {
            // it is this month --THEN--> check if it is the same day
            return this.birthday.getDayOfMonth() == LocalDate.now().getDayOfMonth();
        }
        return false;
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
    public void setDefaultValues(){
        // 1. set today's date to member since / last activity
        this.setMemberSince(LocalDate.now());
        this.setLastActivity(LocalDate.now());
        // 2. set enabled to true
        this.setEnabled(true);
        // 3. set level to 1, xp to 1000 and limit to 2000
        this.setLevel(1);
        this.setXp("1000");
        this.setLevelUpLimit("2000");
        // 4. set user details to null/empty/blank
        this.setName("");
        this.setBirthday(null);
        this.setProvince(Province.HIDDEN);
        // 5. create empty lists for posts / tasks / gardens
        this.setPosts(new ArrayList<Post>());
        this.setTasks(new ArrayList<Task>());
        this.setGardens(new ArrayList<Garden>());
    }

    public long getNewLimit(long currentLimit) {
        return 1000 + Math.round(currentLimit * 1.1);
    }
    public UserResponse getUserProfile() {
        UserResponse thisProfile = new UserResponse();
        thisProfile.setUsername(this.getUsername());
        thisProfile.setName(this.getName());
        thisProfile.setAuthorities(this.getAuthorities());
        return thisProfile;
    }
    public HashMap<Garden, ArrayList<UserResponse>> getGardens(){
        HashMap<Garden, ArrayList<UserResponse>> myGardens = new HashMap<>();
        for (Garden g : this.gardens) {
            myGardens.put(g, g.getOwners());
        }
        return myGardens;
    }

    /**
     * Method for user authorithies
     * CHECK if String is a possible authorithy to add -> isPossibleAuthority()
     * CHECK if String is standard of authorthyString, if not standarize -> standarizeAuthorityString()
     * READ all -> getAuthorithies()
     * ADD 1 -> addAuthority(Authority)
     * ADD 1 -> addAuthority(String)
     * DEL 1 -> removeAuthority(Authority)
     * DEL 1 -> removeAuthority(String)
     */
    private boolean isPossibleAuthority(String toAdd) {
        // 1. create list to store possible authorithies
        List<String> possible = new ArrayList<>();
        // 2. add all accepted authorithies
        possible.add("ROLE_USER");
        possible.add("ROLE_MODERATOR");
        possible.add("ROLE_ADMIN");
        // 3. check for each role if the role is a possible role
        for (String role : possible) { if (role.equals( toAdd )) return true; }
        return false;
    }
    private static String standarizeAuthorityString( String authorityString ) {
        // 1. make sure the String is in UpperCase
        String s = authorityString.toUpperCase();
        // 2. check if String starts with ROLE_ -> if not -> add ROLE_
        if (!s.startsWith("ROLE_")) { s = "ROLE_" + s; }
        // 3. return the standarized String
        return s;
    }

    public List<String> getAuthorities() {
        // 1. create list to story authorithies
        List<String> authorithyStrings = new ArrayList<>();
        for ( Authority authority : this.authorities ) {
            // 2. get the String from each authority from user
            authorithyStrings.add(authority.getAuthority());
        }
        return authorithyStrings;
    }

    public void addAuthority(Authority authority) {
        // 1. get the authorityString from authority
        String s = authority.getAuthority();
        // 2. make sure it is standarized
        s = standarizeAuthorityString( s );
        // 3. check if it is a possible authority
        if ( isPossibleAuthority( s )) {
            // 4A. add to authorithies
            this.authorities.add( new Authority( this.username, s ));
        } else {
            //4B. throw bad request exception
            throw new BadRequestException( "authority is not possible authority" );
        }
    }
    public void addAuthority(String s) {
        // 1. make sure the String is standarized
        s = standarizeAuthorityString( s );
        // 2. check if is possible authority
        if ( isPossibleAuthority( s )) {
            // 3A. add to authorithies
            this.authorities.add( new Authority( this.username, s ));
        } else {
            // 3B. throw bad request
            throw new BadRequestException( "authority is not possible authority" );
        }
    }

    public void removeAuthority(Authority authority) {
        this.authorities.removeIf(auth -> auth == authority );
    }
    public void removeAuthority(String s) {
        String toRemove = standarizeAuthorityString( s );
        this.authorities.removeIf(auth -> Objects.equals(auth.getAuthority(), toRemove));
    }

    /**
     * Methods for leveling up and adding XP
     * - CHECK if user is MAX level
     * - UPDATE level up
     * - SET userXP
     */
    public void checkAndSetMax(long current) {
        // 1. if current level is 99, user is MAX_LEVEL
        if (current == 99) {
            this.setLevel("MAX");
            this.setLevelUpLimit("MAX");
        }
    }
    public void levelUp() {
        // 1. get current level
        long lvl = parseLong(this.level);
        // 2. check if user is level MAX
        checkAndSetMax(lvl);    // user is already MAX
        lvl++;                  // level + 1
        checkAndSetMax(lvl);    // user is now MAX
        // 3. set level to user
        this.setLevel(lvl);
    }
    public void setUserXp (String xp){
        // 1. check if user is not max
        if (!this.xp.equals("MAX")) {
            Long current = parseLong(this.xp);
            Long toAdd = parseLong(xp);
            // add xp
            setXp("" + ( current + toAdd ) );
        }

        long newXP = parseLong(this.xp);
        long limit =  parseLong(this.levelUpLimit);
        // 2. check if the newXP is higher than level up limit
        if (newXP >= limit) {
            // set new limit based on current limit
            setLevelUpLimit( getNewLimit(limit) );
            // 3A. level up user
            this.levelUp();
        }
    }

    /**
     * Methods for users posts
     * - GET post from user by id
     * - CHECK if user has post
     * - ADD post to posts
     * - DEL post from posts
     */
    public Post findPostById(int id) {
        return this.getPosts().get(id);
    }
    public boolean hasPost(Post post) {
        for ( Post p : this.getPosts() ) { if ( p.equals(post) ) return true; }
        return false;
    }
    public void addPost(Post post){
        this.posts.add(post);
    }
    public void removePost(Post post){
        this.posts.removeIf(p -> !this.hasPost(p));
    }

    /**
     * Methods for users tasks
     * - GET task from user by id
     * - GET all tasks by Type
     * - CHECK if user has task
     * - ADD 1 task
     * - DEL 1 task
     */
    public Task findTaskById(int id) {
        return this.getTasks().get(id);
    }
    public ArrayList<Task> getTasksByType(TaskType type) {
        // 1. make a list to store the types
        ArrayList<Task> tasksByType = new ArrayList<>();
        // 2. for each task check if equals type, if true -> add to list
        for (Task t : this.getTasks()) { if (t.getType() == type) tasksByType.add(t); }
        // 3. return the list of tasks
        return tasksByType;
    }
    public boolean hasTask(Task task) {
        for ( Task t : this.getTasks() ) { if ( t.equals(task) ) return true; }
        return false;
    }
    public void addTask(Task task){
        this.tasks.add(task);
    }
    public void removeTask(Task task){
        this.tasks.removeIf(t -> !this.hasTask(t));
    }

}
