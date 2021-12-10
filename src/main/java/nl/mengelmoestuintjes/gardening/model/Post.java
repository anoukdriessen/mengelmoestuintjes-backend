package nl.mengelmoestuintjes.gardening.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="posts")
public class Post {

    // attributen
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // dit wordt de primary key in de database

    private String title;
    private String description;

//    private User author;

    private Date created;
    private Date modified;

    // constructor is niet nodig

    // getters & setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public User getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(User author) {
//        this.author = author;
//    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}
