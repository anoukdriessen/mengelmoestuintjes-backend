package nl.mengelmoestuintjes.gardening.controller.dto.task;

import nl.mengelmoestuintjes.gardening.model.tasks.ToDoTask;

import java.util.Date;

public class ToDoTaskResponseDto {

    public int id;
    public String title;
    public String description;
    public boolean done;
    public Date dueDate;

    public static ToDoTaskResponseDto fromTask(ToDoTask task) {
        ToDoTaskResponseDto dto = new ToDoTaskResponseDto();

        dto.id = task.getId();
        dto.title = task.getTitle();
        dto.description = task.getDescription();
        dto.done = task.isDone();
        dto.dueDate = task.getDueDate();

        return dto;
    }

}
