package nl.mengelmoestuintjes.gardening.controller.dto;

import nl.mengelmoestuintjes.gardening.model.tasks.Task;
import nl.mengelmoestuintjes.gardening.model.tasks.TypeTask;

import java.time.LocalDate;

public class TaskResponseDto {
    public long id;
    public TypeTask type;
    public String title;
    public String description;
    public boolean done;
    public LocalDate starting;
    public LocalDate dueDate;
    public long points;

    public static TaskResponseDto fromTask ( Task toUse ) {
        TaskResponseDto dto = new TaskResponseDto();

        dto.id = toUse.getId();
        dto.type = toUse.getType();
        dto.title = toUse.getTitle();
        dto.description = toUse.getDescription();
        dto.done = toUse.getIsDone();
        dto.starting = toUse.getStarting();
        dto.dueDate = toUse.getDueDate();
        dto.points = toUse.getPoints();

        return dto;

    }
}
