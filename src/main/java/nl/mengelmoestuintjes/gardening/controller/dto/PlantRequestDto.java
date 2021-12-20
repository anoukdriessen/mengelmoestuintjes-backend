package nl.mengelmoestuintjes.gardening.controller.dto;

import nl.mengelmoestuintjes.gardening.model.plants.*;

public class PlantRequestDto {

    public Category category;
    public String name;
    public String description;
    public Location location;

    public Plant toPlant() {
        return new Plant (
                category,
                name,
                description,
                location
        );
    }
}
