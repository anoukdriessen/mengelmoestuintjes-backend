package nl.mengelmoestuintjes.gardening.controller.dto.task;

import nl.mengelmoestuintjes.gardening.model.tasks.GardeningTask;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class GardeningTaskRequestDto {

    @NotBlank
    public String title;
    @NotBlank
    public String description;
    @NotNull
    public boolean done;
    public long points;
    public int month;

    public GardeningTask toTask() {
        return new GardeningTask(
                title,
                description,
                done,
                points,
                month
        );
    }
}
