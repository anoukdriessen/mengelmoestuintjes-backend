package nl.mengelmoestuintjes.gardening.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.mengelmoestuintjes.gardening.model.User;
import nl.mengelmoestuintjes.gardening.model.Post;
import nl.mengelmoestuintjes.gardening.model.PostCategory;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {

    private long id;
    private User author;
    private String title;
    private String summary;
    private String description;
    private String imageUrl;
    private PostCategory category;
    private boolean published;
    private LocalDateTime created;
    private LocalDateTime modified;

    public void setCategory(PostCategory category) {
        this.category = category;
    }
    public void setCategory(String category) {
        switch (category.toUpperCase()) {
            case "POST":
                this.setCategory(PostCategory.POST);
                break;
            case "NOTE":
                this.setCategory(PostCategory.NOTE);
                break;
            case "BLOG":
                this.setCategory(PostCategory.BLOG);
                break;
            case "LEARNING":
                this.setCategory(PostCategory.LEARNING);
                break;
        }
    }
    public Post convert() {
        Post p = new Post();
        p.setId(this.getId());
        p.setAuthor(this.getAuthor());
        p.setTitle(this.getTitle());
        p.setSummary(this.getSummary());
        p.setDescription(this.getDescription());
        p.setImageUrl(this.getImageUrl());
        p.setCategory(this.getCategory());
        p.setPublished(this.isPublished());
        p.setCreated(this.getCreated());
        p.setModified(this.getModified());
        return p;
    }
}
