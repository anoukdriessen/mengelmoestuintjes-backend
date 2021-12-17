package nl.mengelmoestuintjes.gardening.model.posts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import nl.mengelmoestuintjes.gardening.model.users.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="posts")
public class Post {

    /*
     * Post bestaat uit:
     * - titel
     * - category (type van post)
     * - beschrijving
     * - afbeelding (optioneel)
     * - auteur (gebruikersnaam)
     * - datum van aanmaken
     * - datum van wijzigen
     * - zichtbaarheid
     *
     * gebruikers kunnen posts toevoegen aan hun profiel
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private PostCategory category;
    private String description;
    private String imageUrl;
    private String author;
    private Date created;
    private Date modified;
    private boolean visible;

    @JsonIgnoreProperties("posts")
    @ManyToOne
    @JoinColumn( name = "user_id", referencedColumnName = "id")
    private User owner;

    public Post(){}
    public Post( int id, String title, PostCategory category, String description, String imageUrl,
                String author, boolean visible ) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.description = description;
        this.imageUrl = imageUrl;
        this.author = author;
        this.created = new Date(); // set to current date
        this.visible = visible;
    }
    public Post( String title, PostCategory category, String description, String imageUrl,
                String author, boolean visible ) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.imageUrl = imageUrl;
        this.author = author;
        this.created = new Date(); // set to current date
        this.visible = visible;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public PostCategory getCategory() {
        return category;
    }
    public String getDescription() {
        return description;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public String getAuthor() {
        return this.author;
    }
    public Date getCreated() {
        return created;
    }
    public Date getModified() {
        return modified;
    }
    public boolean isVisible() {
        return visible;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setCategory(PostCategory category) {
        this.category = category;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public void setModified(Date modified) {
        this.modified = modified;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
