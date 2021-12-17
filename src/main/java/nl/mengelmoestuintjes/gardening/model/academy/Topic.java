package nl.mengelmoestuintjes.gardening.model.academy;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "name_of_table")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Category category;
    private String title;
    private String description;
    // TODO implement relation
    // private List<Post> posts;

    private LocalDate createdAt;
    private LocalDate modifiedAt;

    public Topic() {}
    public Topic( long id, Category category, String title, String description ) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.description = description;
        this.createdAt = LocalDate.now();
        this.modifiedAt = null;
    }

    public long getId() {
        return id;
    }
    public Category getCategory() {
        return category;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public LocalDate getCreatedAt() {
        return createdAt;
    }
    public LocalDate getModifiedAt() {
        return modifiedAt;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
    public void setModifiedAt(LocalDate modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public String toString() {
        return String.format("%s ) %s \n %s, %s, %s \n %s",
                this.id, this.title,
                this.category, this.createdAt, this.modifiedAt,
                this.description);
    }
}
