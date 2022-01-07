//package nl.mengelmoestuintjes.gardening.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import nl.mengelmoestuintjes.gardening.model.users.User;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "milestones")
//public class Milestone {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    private String title;
//    private String urlToBadge;
//    private long points;
//
//    @JsonIgnoreProperties("milestones")
//    @ManyToOne
//    @JoinColumn( name = "user_id", referencedColumnName = "id")
//    private User owner;
//
//    public Milestone(){}
//    public Milestone(long id, String title, String urlToBadge, long points, User owner) {
//        this.id = id;
//        this.title = title;
//        this.urlToBadge = urlToBadge;
//        this.points = points;
//        this.owner = owner;
//    }
//    public Milestone(String title, String urlToBadge, long points) {
//        this.title = title;
//        this.urlToBadge = urlToBadge;
//        this.points = points;
//    }
//
//
//    public long getId() {
//        return id;
//    }
//    public String getTitle() {
//        return title;
//    }
//    public String getUrlToBadge() {
//        return urlToBadge;
//    }
//    public long getPoints() {
//        return points;
//    }
//    public User getOwner() {
//        return owner;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//    public void setTitle(String title) {
//        this.title = title;
//    }
//    public void setUrlToBadge(String urlToBadge) {
//        this.urlToBadge = urlToBadge;
//    }
//    public void setPoints(long points) {
//        this.points = points;
//    }
//    public void setOwner(User owner) {
//        this.owner = owner;
//    }
//
//    @Override
//    public String toString() {
//        return String.format("%l ) %s - %s [%l]", this.id, this.title, this.urlToBadge, this.points);
//    }
//}
