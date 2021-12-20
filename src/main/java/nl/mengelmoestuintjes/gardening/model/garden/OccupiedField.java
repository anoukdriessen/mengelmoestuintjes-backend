package nl.mengelmoestuintjes.gardening.model.garden;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class OccupiedField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime occupiedSince;
    private LocalDateTime harvestOn;

    @ManyToOne
    @JsonManagedReference
    private Field field;

//    @OneToMany
//    @JsonIgnore
//    private List<Flower> flowers;
//
//    @OneToMany
//    @JsonIgnore
//    private List<Fruit> fruits;
//
//    @OneToMany
//    @JsonIgnore
//    private List<Herb> herbs;
//
//    @OneToMany
//    @JsonIgnore
//    private List<Vegetable> vegetables;
}
