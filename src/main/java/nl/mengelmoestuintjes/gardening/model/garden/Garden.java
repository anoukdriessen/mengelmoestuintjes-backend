package nl.mengelmoestuintjes.gardening.model.garden;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import nl.mengelmoestuintjes.gardening.model.users.User;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="gardens")
public class Garden {

    @Id
    @Column(name = "garden_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    int x;
    int y;

    public Garden(){};
    public Garden(int id, String name, int x, int y, List<Field> fields, User owner) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.fields = fields;
        this.owner = owner;
    }


    @OneToMany
    @JsonIgnore
    private List<Field> fields;

    public int getId() {
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

    public void setId(int id) {
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
