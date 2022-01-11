package nl.mengelmoestuintjes.gardening.model.garden;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import nl.mengelmoestuintjes.gardening.model.Task;
import nl.mengelmoestuintjes.gardening.model.TaskType;
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

    private int x;
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

    @JsonIgnoreProperties("gardens")
    @OneToMany(
            fetch=FetchType.LAZY,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                      CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "task_id")
    private List<Task> tasks = new ArrayList<>();

//    @OneToMany
//    @JsonIgnore
//    private List<Field> fields;

    public int calculateSize(int x, int y) {
        return x * y;
    }

    public void setSize(int x, int y) {
        this.size = "" + (calculateSize(x, y));
    }

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
    public void setTasks() {
        for (User u : this.owners) {
            tasks.addAll(u.getTasksByType(TaskType.GARDENING));
        }
    }
    public void addTask(Task toAdd) {
        toAdd.setType(TaskType.GARDENING);
        tasks.add(toAdd);
    }
    public void removeTask(Task toRemove) {
        if (!tasks.isEmpty()) {
            tasks.remove(toRemove);
        }
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
