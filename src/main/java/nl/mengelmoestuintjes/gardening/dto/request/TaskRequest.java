package nl.mengelmoestuintjes.gardening.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.mengelmoestuintjes.gardening.model.Task;
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

    public void setType(TaskType type) {
        this.type = type;
    }
    public void setType(String type) {
        switch (type.toUpperCase()){
            case "TODO":
                this.setType(TaskType.TODO);
                break;
            case "GARDENING":
                this.setType(TaskType.GARDENING);
                break;
        }
    }


    public Task convert(){
        Task t = new Task();
        t.setId(this.getId());
        t.setOwner(this.getOwner());
        t.setType(this.getType());
        t.setTitle(this.getTitle());
        t.setDone(this.isDone());
        t.setCreated(this.getCreated());
        t.setDeadline(this.getDeadline());
        return t;
    }
}
