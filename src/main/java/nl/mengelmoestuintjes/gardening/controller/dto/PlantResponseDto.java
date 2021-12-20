package nl.mengelmoestuintjes.gardening.controller.dto;

import nl.mengelmoestuintjes.gardening.model.plants.*;

public class PlantResponseDto {
    public long id;
    public Category category;
    public String name;
    public String description;
    public Location location;

    public static PlantResponseDto fromPlant(Plant plant) {
        PlantResponseDto dto = new PlantResponseDto();

        dto.id = plant.getId();
        dto.category = plant.getCategory();
        dto.name = plant.getName();
        dto.description = plant.getDescription();
        dto.location = plant.getLocation();

        return dto;
    }
}
