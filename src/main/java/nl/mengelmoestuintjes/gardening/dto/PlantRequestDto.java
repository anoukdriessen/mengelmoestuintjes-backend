package nl.mengelmoestuintjes.gardening.dto;

import nl.mengelmoestuintjes.gardening.model.plants.*;

public class PlantRequestDto {

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


    public Plant toPlant() {
        Plant plant = new Plant (
                category,
                name,
                description
        );

        plant.setLocation(location);
        plant.setOutdoors(outdoors);
        plant.setIndoors(indoors);
        plant.setHarvest(harvest);
        plant.setFrostResistance(frostResistance);
        plant.setWaterRequirement(waterRequirement);
        plant.setForPreSow(forPreSow);
        plant.setForSeedCollecting(forSeedCollecting);
        plant.setForPot(forPot);
        plant.setForOutdoors(forOutdoors);
        plant.setForIndoors(forIndoors);
        plant.setForGreenhouse(forGreenhouse);
        plant.setForVertical(forVertical);
        plant.setDepth(depth);
        plant.setDistanceBetweenPlants(distanceBetweenPlants);
        plant.setDistanceBetweenRows(distanceBetweenRows);
        plant.setAvgDaysGermination(avgDaysGermination);
        plant.setAvgDaysHarvest(avgDaysHarvest);
        plant.setVisible(visible);

        return plant;
    }
}
