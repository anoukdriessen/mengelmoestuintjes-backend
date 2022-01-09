package nl.mengelmoestuintjes.gardening.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.mengelmoestuintjes.gardening.model.posts.PostCategory;
import nl.mengelmoestuintjes.gardening.model.users.User;

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

}
