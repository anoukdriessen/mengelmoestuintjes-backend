package nl.mengelmoestuintjes.gardening.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.mengelmoestuintjes.gardening.model.Task;

import javax.persistence.Lob;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private String username;
    private String name;
    @Lob
    private byte[] image;
    private List<Task> tasks = new ArrayList<>();
    private List<String> authorities = new ArrayList<>();

}
