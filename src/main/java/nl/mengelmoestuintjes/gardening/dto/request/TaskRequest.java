package nl.mengelmoestuintjes.gardening.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.mengelmoestuintjes.gardening.model.TaskType;
import nl.mengelmoestuintjes.gardening.model.User;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {
    private Long id;
    private User owner;
    private TaskType type;
    private String title;
    private String description;
    private boolean done;
    private LocalDate created;
    private LocalDate deadline;

}
