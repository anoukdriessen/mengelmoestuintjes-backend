//package nl.mengelmoestuintjes.gardening.model.garden;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import lombok.Data;
//import nl.mengelmoestuintjes.gardening.model.garden.plants.*;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Data
//@Entity
//@Table(name = "gardens")
//public class OccupiedField {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    private LocalDateTime occupiedSince;
//    private LocalDateTime harvestOn;
//
//    @ManyToOne
//    @JsonManagedReference
//    private Field field;
//
//    @OneToMany
//    @JsonIgnore
//    private List<Plant> plants;
//
//}
