package nl.mengelmoestuintjes.gardening.model.plants;

import javax.persistence.*;

@Entity
@Table(name = "plant_flowers")
public class Flower extends Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;               // de naam van de plant
    private String bloom;              // maanden waarin de plant bloeit

    public Flower() {
        super();
    }
    public Flower(int id, String name, String family, String description, String location, String ground, Boolean forPreSow, Boolean forSeedCollecting, Boolean forPot, Boolean forOutside, Boolean forIndoor, Boolean forGreenhouse, Boolean forVerticalGarden, String sowOutdoors, String sowIndoors, String harvest, boolean frostResistance, int waterRequirement, int depth, int distanceBetweenPlants, int distanceBetweenRows, int plantsPerMeter, int plantsPerSquare, int avgDaysGermination, int avgDaysHarvest, String bloom) {
        super(family, description, location, ground, forPreSow, forSeedCollecting, forPot, forOutside, forIndoor, forGreenhouse, forVerticalGarden, sowOutdoors, sowIndoors, harvest, frostResistance, waterRequirement, depth, distanceBetweenPlants, distanceBetweenRows, plantsPerMeter, plantsPerSquare, avgDaysGermination, avgDaysHarvest);
        this.id = id;
        this.name = name;
        this.bloom = bloom;
    }
    public Flower(String name, String family, String description, String location, String ground, String sowOutdoors, String sowIndoors, String harvest, int depth, int distanceBetweenPlants, int distanceBetweenRows, int avgDaysGermination, int avgDaysHarvest, String harvest1) {
        super(family, description, location, ground, sowOutdoors, sowIndoors, harvest, depth, distanceBetweenPlants, distanceBetweenRows, avgDaysGermination, avgDaysHarvest);
        this.name = name;
        this.bloom = bloom;
    }

    public int getId() {
        return id;
    }
    public String getBloom() {
        return bloom;
    }
    public String getName() {
        return name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setBloom(String bloom) {
        this.bloom = bloom;
    }
}
