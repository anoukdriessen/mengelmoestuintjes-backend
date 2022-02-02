package nl.mengelmoestuintjes.gardening.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.mengelmoestuintjes.gardening.model.Post;
import nl.mengelmoestuintjes.gardening.model.garden.Field;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GardenResponse {

    Long id;
    String name;
    String size;
    List<Post> posts = new ArrayList<>();
    List<Field> fields = new ArrayList<>();
    ArrayList<UserResponse> profiles = new ArrayList<>();

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public ArrayList<UserResponse> getProfiles() {
        return profiles;
    }

    public void setProfiles(ArrayList<UserResponse> profiles) {
        this.profiles = profiles;
    }
}
