package nl.mengelmoestuintjes.gardening.controller.dto;

import nl.mengelmoestuintjes.gardening.model.plants.*;

public class PlantResponseDto {
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

    // TODO plant variabelen worden niet geupdate
    public void setPlantAttributes(Plant plant ) {
        this.family = plant.getFamily();
        this.description = plant.getDescription();
        this.location = plant.getLocation();
        this.ground = plant.getGround();
        this.forPreSow = plant.getForPreSow();
        this.forSeedCollecting = plant.getForSeedCollecting();
        this.forPot = plant.getForPot();
        this.forOutside = plant.getForOutside();
        this.forIndoor = plant.getForIndoor();
        this.forGreenhouse = plant.getForGreenhouse();
        this.forVerticalGarden = plant.getForVerticalGarden();
        this.sowOutdoors = plant.getSowOutdoors();
        this.sowIndoors = plant.getSowIndoors();
        this.harvest = plant.getHarvest();
        this.frostResistance = plant.getFrostResistance();
        this.waterRequirement = plant.getWaterRequirement();
        this.depth = plant.getDepth();
        this.distanceBetweenPlants = plant.getDistanceBetweenPlants();
        this.distanceBetweenRows = plant.getDistanceBetweenRows();
        this.plantsPerMeter = plant.getPlantsPerMeter();
        this.plantsPerSquare = plant.getPlantsPerSquare();
        this.avgDaysGermination = plant.getAvgDaysGermination();
        this.avgDaysHarvest = plant.getAvgDaysHarvest();
    }
    public static PlantResponseDto fromFlower(Flower flower) {
        PlantResponseDto dto = new PlantResponseDto();
        dto.setPlantAttributes(flower);
        dto.id = flower.getId();
        dto.name = flower.getName();
        dto.bloom = flower.getBloom();
        return dto;
    }
    public static PlantResponseDto fromFruit(Fruit fruit) {
        PlantResponseDto dto = new PlantResponseDto();
        dto.setPlantAttributes(fruit);
        dto.id = fruit.getId();
        dto.name = fruit.getName();
        dto.length = fruit.getLength();
        return dto;
    }
    public static PlantResponseDto fromVegetable(Vegetable vegetable) {
        PlantResponseDto dto = new PlantResponseDto();
        dto.setPlantAttributes(vegetable);
        dto.id = vegetable.getId();
        dto.name = vegetable.getName();
        return dto;
    }
    public static PlantResponseDto fromHerb(Herb herb) {
        PlantResponseDto dto = new PlantResponseDto();
        dto.setPlantAttributes(herb);
        dto.id = herb.getId();
        dto.name = herb.getName();
        dto.goodForTea = herb.isGoodForTea();
        return dto;

    }
}
