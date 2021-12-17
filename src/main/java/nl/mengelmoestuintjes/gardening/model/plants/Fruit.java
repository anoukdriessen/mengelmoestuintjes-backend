package nl.mengelmoestuintjes.gardening.model.plants;

import javax.persistence.*;

@Entity
@Table(name = "plant_fruits")
public class Fruit extends Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;                // de naam van de plant
    private int length;

    public Fruit() {
        super();
    }
    public Fruit(int id, String name, String family, String description, String location, String ground, Boolean forPreSow, Boolean forSeedCollecting, Boolean forPot, Boolean forOutside, Boolean forIndoor, Boolean forGreenhouse, Boolean forVerticalGarden, String sowOutdoors, String sowIndoors, String harvest, boolean frostResistance, int waterRequirement, int depth, int distanceBetweenPlants, int distanceBetweenRows, int plantsPerMeter, int plantsPerSquare, int avgDaysGermination, int avgDaysHarvest, int length) {
        super(family, description, location, ground, forPreSow, forSeedCollecting, forPot, forOutside, forIndoor, forGreenhouse, forVerticalGarden, sowOutdoors, sowIndoors, harvest, frostResistance, waterRequirement, depth, distanceBetweenPlants, distanceBetweenRows, plantsPerMeter, plantsPerSquare, avgDaysGermination, avgDaysHarvest);
        this.id = id;
        this.name = name;
        this.length = length;
    }
    public Fruit(String name, String family, String description, String location, String ground, String sowOutdoors, String sowIndoors, String harvest, int depth, int distanceBetweenPlants, int distanceBetweenRows, int avgDaysGermination, int avgDaysHarvest, int length) {
        super(family, description, location, ground, sowOutdoors, sowIndoors, harvest, depth, distanceBetweenPlants, distanceBetweenRows, avgDaysGermination, avgDaysHarvest);
        this.name = name;
        this.length = length;
    }

    public int getId() {
        return id;
    }
    public int getLength() {
        return length;
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
    public void setLength(int length) {
        this.length = length;
    }
}
