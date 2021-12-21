package nl.mengelmoestuintjes.gardening.dto;

import nl.mengelmoestuintjes.gardening.model.tasks.Task;
import nl.mengelmoestuintjes.gardening.model.tasks.TypeTask;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class TaskRequestDto {
    @NotNull
    public TypeTask type;

    @NotNull
    @Size(min=1, max=30)
    public String title;

    @NotBlank
    public String description;

    public boolean done;
    public LocalDate starting;
    public LocalDate dueDate;
    public long points;

    public Task toTask() {
        Task task = new Task();
        task.setType(this.type);
        task.setTitle(this.title);
        task.setDescription(this.description);
        task.setIsDone(this.done);
        task.setStarting(this.starting);
        task.setDueDate(this.dueDate);
        task.setPoints(this.points);

        return task;
    }
}
