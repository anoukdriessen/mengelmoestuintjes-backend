//package nl.mengelmoestuintjes.gardening.model.garden;
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//import lombok.Data;
//
//@Data
//@Entity
//@Table(name = "tuintjes")
//public class Field {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    private FieldStatus status;
//
//    @ManyToOne
//    private Garden garden; // een tuin bevat meerdere velden
//
//    @OneToMany( mappedBy = "field" )
//    @JsonBackReference // een veld kan door meerdere planten bezet worden
//    private List<OccupiedField> occupiedBy = new ArrayList<>();
//}
