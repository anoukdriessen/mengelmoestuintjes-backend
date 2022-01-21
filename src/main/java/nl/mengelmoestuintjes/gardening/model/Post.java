package nl.mengelmoestuintjes.gardening.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="posts")
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties("posts")
    @ManyToOne(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                       CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "username")
    private User author;

    @Column(nullable = false)
    private String title;

    private String summary;


//    @Column(nullable = false)
    @Column(nullable = false, columnDefinition="TEXT")
    private String description;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private PostCategory category;

    private boolean published;

    private LocalDateTime created;
    private LocalDateTime modified;

    public void setModified() {
        this.modified = LocalDateTime.now();
    }
    public String getAuthor() {
        return author.getUsername();
    }
}