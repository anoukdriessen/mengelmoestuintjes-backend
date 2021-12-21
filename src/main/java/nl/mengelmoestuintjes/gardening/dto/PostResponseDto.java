package nl.mengelmoestuintjes.gardening.dto;

import nl.mengelmoestuintjes.gardening.model.posts.Post;
import nl.mengelmoestuintjes.gardening.model.posts.PostCategory;

import java.util.Date;

public class PostResponseDto {

    public int id;
    public String title;
    public PostCategory category;
    public String description;
    public String imageUrl;
    public String author;
    public Date created;
    public Date modified;
    public boolean visible;

    public static PostResponseDto fromPost(Post post) {
        PostResponseDto dto = new PostResponseDto();

        dto.id = post.getId();
        dto.title = post.getTitle();
        dto.category = post.getCategory();
        dto.description = post.getDescription();
        dto.imageUrl = post.getImageUrl();
        dto.author = post.getAuthor();
        dto.created = post.getCreated();
        dto.modified = post.getModified();
        dto.visible = post.isVisible();

        return dto;
    }
}
