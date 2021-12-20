package nl.mengelmoestuintjes.gardening.controller.dto;

import nl.mengelmoestuintjes.gardening.model.plants.*;

public class PlantRequestDto {
    public int id;
    public String name;
    public String family;
    public String description;
    public String location;
    public String ground;
    public Boolean forPreSow;
    public Boolean forSeedCollecting;
    public Boolean forPot;
    public Boolean forOutside;
    public Boolean forIndoor;
    public Boolean forGreenhouse;
    public Boolean forVerticalGarden;
    public String sowOutdoors;
    public String sowIndoors;
    public String harvest;
    public boolean frostResistance;
    public int waterRequirement;
    public int depth;
    public int distanceBetweenPlants;
    public int distanceBetweenRows;
    public int plantsPerMeter;
    public int plantsPerSquare;
    public int avgDaysGermination;
    public int avgDaysHarvest;

    // flower
    public String bloom;

    // fruit
    public int length;

    // herb
    public boolean goodForTea;

    public Flower toFlower() {
        return new Flower(
        );
    }
    public Fruit toFruit() {
        return new Fruit(
        );
    }
    public Herb toHerb() {
        return new Herb(
        );
    }
    public Vegetable toVegetable() {
        return new Vegetable();
    }
}
