package nl.mengelmoestuintjes.gardening.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GardenResponse {

    Long id;
    String name;
    String size;
    int numberOfTasks;
    ArrayList<UserResponse> profiles;
}
