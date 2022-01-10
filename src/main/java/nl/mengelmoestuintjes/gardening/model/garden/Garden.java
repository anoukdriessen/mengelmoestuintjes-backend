package nl.mengelmoestuintjes.gardening.model.garden;


import lombok.Data;
import nl.mengelmoestuintjes.gardening.model.User;

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

    private String size;

    @Transient
    private int x;
    @Transient
    private int y;

    @ManyToMany(
            fetch=FetchType.LAZY,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="garden_users",
            joinColumns=@JoinColumn(name="garden_id"),
            inverseJoinColumns=@JoinColumn(name="user_id")
    )
    private List<User> owners = new ArrayList<>();

//    @OneToMany
//    @JsonIgnore
//    private List<Field> fields;

//    public int calculateSize(byte x, byte y) {
//        return x * y;
//    }

//    public void setFields(List<Field> fields) {
//        this.fields = fields;
//    }

//    public void setSize() {
//        this.size = "" + (calculateSize(this.x, this.y));
//    }

    public boolean hasOwner(User user){
        for (User u : this.owners) {
            if (u.equals(user)) return true;
        }
        return false;
    }
    public void addOwner(User user) {
        if (!hasOwner(user)) this.owners.add(user);
    }
    public void removeOwner(User user) {
        if (hasOwner(user)) this.owners.remove(user);
    }
    public ArrayList<String> getOwners() {
        ArrayList<String> usernames = new ArrayList<>();
        for (User u : this.owners) {
            usernames.add(u.getUsername());
        }
        return usernames;
    }

}

//
//    @JsonGetter("squares")
//    public int getSquares() {
//        return x * y ;
//    }
//
//    @JsonGetter("number_of_fields")
//    public int getNumberOfFields() {
//        return fields.size();
//    }
