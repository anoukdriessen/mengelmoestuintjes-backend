package nl.mengelmoestuintjes.gardening.model.users;

import javax.persistence.*;

@Entity
@Table(name = "milestones")
public class Milestone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;
    private String urlTooBadge;
    private long points;

    public long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getUrlTooBadge() {
        return urlTooBadge;
    }
    public long getPoints() {
        return points;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setUrlTooBadge(String urlTooBadge) {
        this.urlTooBadge = urlTooBadge;
    }
    public void setPoints(long points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return String.format("%l ) %s - %s [%l]", this.id, this.title, this.urlTooBadge, this.points);
    }
}
