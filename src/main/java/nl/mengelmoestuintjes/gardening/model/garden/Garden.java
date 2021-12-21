package nl.mengelmoestuintjes.gardening.model.garden;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import nl.mengelmoestuintjes.gardening.model.users.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="gardens")
public class Garden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    int x;
    int y;

    @OneToMany
    @JsonIgnore
    private List<Field> fields;

    public Garden () {}
    public Garden(long id, String name, int x, int y) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
    }
    public Garden(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    @JsonGetter("squares")
    public int getSquares() {
        return x * y ;
    }

    @JsonGetter("number_of_fields")
    public int getNumberOfFields() {
        return fields.size();
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    @JsonIgnoreProperties("gardens")
    @ManyToOne
    @JoinColumn( name = "user_id", referencedColumnName = "id")
    private User owner;
}
