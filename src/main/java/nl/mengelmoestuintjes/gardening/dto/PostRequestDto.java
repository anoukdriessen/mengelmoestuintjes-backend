package nl.mengelmoestuintjes.gardening.dto;

import nl.mengelmoestuintjes.gardening.model.posts.Post;
import nl.mengelmoestuintjes.gardening.model.posts.PostCategory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PostRequestDto {

    @NotBlank
    @Size(min=1, max=30)
    public String title;

    @NotNull
    public PostCategory category;

    @NotBlank
    @Size(min=1, max = 255)
    public String description;

    public String imageUrl;

    @NotNull
    public String author;

    @NotNull
    public boolean visible;

    public Post toPost() {
        return new Post(
                title,
                category,
                description,
                imageUrl,
                author,
                visible
        );
    }
}
