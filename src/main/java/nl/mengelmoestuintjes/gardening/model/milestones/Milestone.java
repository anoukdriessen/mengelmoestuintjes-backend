//package nl.mengelmoestuintjes.gardening.model;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import lombok.Data;
//
//import javax.persistence.*;
//import java.time.LocalDate;
//
//@Entity
//@Table(name = "milestones")
//@Data
//public class Milestone {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @JsonIgnoreProperties("milestones")
//    @ManyToOne(
//            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
//                       CascadeType.DETACH, CascadeType.REFRESH},
//            fetch = FetchType.EAGER)
//    @JoinColumn(
//            name = "user_id",
//            referencedColumnName = "username")
//    private User owner;
//
//    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
//    private Milestones milestone;
//
//    private LocalDate achieved;
//}