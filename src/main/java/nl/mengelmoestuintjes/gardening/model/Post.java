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
    private String author;
    private Date created;
    private Date modified;

    // constructor is niet nodig bij Spring Boot
    // Spring Boot maakt gebruik van de default constructor
    // als je er een maakt moet je ze allemaal maken
    // lege constructor
    public Post(){}
    // full constructor
    public Post(int id, String title, String description, String author, Date modified){
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.created = new Date();
        this.modified = modified;
    }
    public Post(String title, String description, String author) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.created = new Date(); // current date
    }

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

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

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
