package nl.mengelmoestuintjes.gardening.controller.dto.task;

import nl.mengelmoestuintjes.gardening.model.tasks.ToDoTask;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class ToDoTaskRequestDto {

    @NotBlank
    public String title;
    @NotBlank
    public String description;
    @NotNull
    public boolean done;
    public Date dueDate;

    public ToDoTask toTask() {
        return new ToDoTask(
                title,
                description,
                done,
                dueDate
        );
    }
}
