package nl.mengelmoestuintjes.gardening.model.garden;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import nl.mengelmoestuintjes.gardening.model.garden.plants.Plant;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fields")
@Data
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private FieldStatus status;

    @ManyToOne(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "garden_id")
    private Garden garden;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinTable(
            name="fields_plants",
            joinColumns = @JoinColumn(name="field_id"),
            inverseJoinColumns=@JoinColumn(name="plant_id")
    )
    private List<Plant> occupiedBy = new ArrayList<>();

    public void setStatus(FieldStatus status){
        this.status = status;
    }
    public void setStatus(String status) {
        switch (status.toUpperCase()) {
            case "EMPTY":
                this.setStatus(FieldStatus.EMPTY);
                break;
            case "PATH":
                this.setStatus(FieldStatus.PATH);
                break;
            case "GRASS":
                this.setStatus(FieldStatus.GRASS);
                break;
            case "PLANTABLE":
                this.setStatus(FieldStatus.PLANTABLE);
                break;
            case "PLANTED":
                this.setStatus(FieldStatus.PLANTED);
                break;
            case "PLANTED AND WATERED":
                this.setStatus(FieldStatus.PLANTED_AND_WATERED);
                break;
        }
    }

    public Long getGarden() {
        return garden.getId();
    }
}
