package nl.mengelmoestuintjes.gardening.controller.dto.task;

import nl.mengelmoestuintjes.gardening.model.tasks.GardeningTask;

public class GardeningTaskResponseDto {

    public int id;
    public String title;
    public String description;
    public boolean done;
    public long points;
    public int month;

    public static GardeningTaskResponseDto fromTask(GardeningTask task) {
        GardeningTaskResponseDto dto = new GardeningTaskResponseDto();

        dto.id = task.getId();
        dto.title = task.getTitle();
        dto.description = task.getDescription();
        dto.done = task.isDone();
        dto.points = task.getPoints();
        dto.month = task.getMonth();

        return dto;
    }
}
