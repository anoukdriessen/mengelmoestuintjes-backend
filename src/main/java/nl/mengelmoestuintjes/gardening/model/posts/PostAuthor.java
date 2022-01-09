package nl.mengelmoestuintjes.gardening.model.posts;

import lombok.Data;
import nl.mengelmoestuintjes.gardening.model.users.User;

import javax.persistence.*;

@Entity
@Table(name = "post_author")
@Data
public class PostAuthor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn( name = "author_id")
    User author;

    @ManyToOne
    @JoinColumn( name = "post_id")
    Post post;

    public PostAuthor(){}
    public PostAuthor(long id, User author, Post post) {
        this.id = id;
        this.author = author;
        this.post = post;
    }
    public PostAuthor(User author, Post post) {
        this.author = author;
        this.post = post;
    }
}
