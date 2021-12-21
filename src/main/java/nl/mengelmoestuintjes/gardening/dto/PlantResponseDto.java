package nl.mengelmoestuintjes.gardening.dto;

import nl.mengelmoestuintjes.gardening.model.plants.*;

public class PlantResponseDto {
    public long id;
    public Category category;
    public String name;
    public String description;
    public Location location;
    public String outdoors;
    public String indoors;
    public String harvest;
    public Requirement frostResistance;
    public Requirement waterRequirement;
    public boolean forPreSow;
    public boolean forSeedCollecting;
    public boolean forPot;
    public boolean forOutdoors;
    public boolean forIndoors;
    public boolean forGreenhouse;
    public boolean forVertical;
    public int depth;
    public int distanceBetweenPlants;
    public int distanceBetweenRows;
    public int avgDaysGermination;
    public int avgDaysHarvest;
    public boolean visible;

    public static PlantResponseDto fromPlant(Plant plant) {
        PlantResponseDto dto = new PlantResponseDto();

        dto.id = plant.getId();
        dto.category = plant.getCategory();
        dto.name = plant.getName();
        dto.description = plant.getDescription();
        dto.location = plant.getLocation();
        dto.outdoors = plant.getOutdoors();
        dto.indoors = plant.getIndoors();
        dto.harvest = plant.getHarvest();
        dto.frostResistance = plant.getFrostResistance();
        dto.waterRequirement = plant.getWaterRequirement();
        dto.forPreSow = plant.isForPreSow();
        dto.forSeedCollecting = plant.isForSeedCollecting();
        dto.forPot = plant.isForPot();
        dto.forOutdoors = plant.isForOutdoors();
        dto.forIndoors = plant.isForIndoors();
        dto.forGreenhouse = plant.isForGreenhouse();
        dto.forVertical = plant.isForVertical();
        dto.depth = plant.getDepth();
        dto.distanceBetweenPlants = plant.getDistanceBetweenPlants();
        dto.distanceBetweenRows = plant.getDistanceBetweenRows();
        dto.avgDaysGermination = plant.getAvgDaysGermination();
        dto.avgDaysHarvest = plant.getAvgDaysHarvest();
        dto.visible = plant.isVisible();

        return dto;
    }
}
