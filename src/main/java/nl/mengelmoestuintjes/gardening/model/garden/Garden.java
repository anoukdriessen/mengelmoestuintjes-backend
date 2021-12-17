package nl.mengelmoestuintjes.gardening.model.garden;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="gardens")
public class Garden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    int x;
    int y;

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
}
