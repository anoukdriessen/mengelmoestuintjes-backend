package nl.mengelmoestuintjes.gardening.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class PostRequestDto {

    @NotBlank(message = "title cannot be empty")
    @Size(min=1, max=25, message = "size must be between 1 and 100")
    private String title;

    @NotBlank(message = "description cannot be empty")
    private String description;

    @NotNull(message = "author cannot be null")
    private String author;
    private Date created;
    private Date modified;

    // getters & setters

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
        return author;
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
