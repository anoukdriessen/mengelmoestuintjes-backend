package nl.mengelmoestuintjes.gardening.model.garden;

import nl.mengelmoestuintjes.gardening.dto.response.UserResponse;
import nl.mengelmoestuintjes.gardening.model.*;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="gardens")
@Data
public class Garden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private int x;
    private int y;
    private String size;

    @ManyToMany(
            fetch=FetchType.LAZY,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                      CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="gardens_users",
            joinColumns=@JoinColumn(name="garden_id"),
            inverseJoinColumns=@JoinColumn(name="user_id")
    )
    private List<User> owners = new ArrayList<>();

    @OneToMany(
            fetch=FetchType.LAZY,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="gardens_posts",
            joinColumns=@JoinColumn(name="garden_id"),
            inverseJoinColumns=@JoinColumn(name="post_id")
    )
    private List<Post> posts = new ArrayList<>();

    @OneToMany(
            fetch=FetchType.LAZY,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="gardens_fields",
            joinColumns=@JoinColumn(name="garden_id"),
            inverseJoinColumns=@JoinColumn(name="field_id")
    )
    private List<Field> fields = new ArrayList<>();

    public int calculateSize(int x, int y) {
        return x * y;
    }
    public void setSize(int x, int y) {
        this.size = "" + (calculateSize(x, y));
    }

    public boolean hasOwner(String username){
        for (User u : this.owners) { if (u.getUsername().equals(username)) return true; }
        return false;
    }
    public void addOwner(User user) {
        if (!hasOwner(user.getUsername())) this.owners.add(user);
    }
    public void removeOwner(User user) {
        if (hasOwner(user.getUsername())) this.owners.remove(user);
    }
    public ArrayList<UserResponse> getOwners() {
        ArrayList<UserResponse> profiles = new ArrayList<>();
        for (User u : this.owners) {
            UserResponse thisUser = new UserResponse(
                    u.getUsername(),
                    u.getName(),
                    u.getProfileImg(),
                    u.getTasks(),
                    u.getAuthorities());
            profiles.add(thisUser);
        }
        return profiles;
    }

    public void addPost(Post toAdd) {
        toAdd.setCategory(PostCategory.NOTE);
        posts.add(toAdd);
    }
    public void removePost(Post toRemove) {
        if (!posts.isEmpty()) {
            posts.remove(toRemove);
        }
    }

    public Field findFieldById(int id) {
        return this.fields.get(id);
    }
    public void addField(Field toAdd) {
        this.fields.add(toAdd);
    }
    public void removeField(Field toRemove) {
        this.fields.remove(toRemove);
    }
}


