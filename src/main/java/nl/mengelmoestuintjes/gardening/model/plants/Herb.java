package nl.mengelmoestuintjes.gardening.model.plants;

import javax.persistence.*;

@Entity
@Table(name = "plant_herbs")
public class Herb extends Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean goodForTea;
    private String name;                // de naam van de plant

    public Herb() {
        super();
    }
    public Herb(int id, String name, String family, String description, String location, String ground, Boolean forPreSow, Boolean forSeedCollecting, Boolean forPot, Boolean forOutside, Boolean forIndoor, Boolean forGreenhouse, Boolean forVerticalGarden, String sowOutdoors, String sowIndoors, String harvest, boolean frostResistance, int waterRequirement, int depth, int distanceBetweenPlants, int distanceBetweenRows, int plantsPerMeter, int plantsPerSquare, int avgDaysGermination, int avgDaysHarvest, boolean goodForTea) {
        super(family, description, location, ground, forPreSow, forSeedCollecting, forPot, forOutside, forIndoor, forGreenhouse, forVerticalGarden, sowOutdoors, sowIndoors, harvest, frostResistance, waterRequirement, depth, distanceBetweenPlants, distanceBetweenRows, plantsPerMeter, plantsPerSquare, avgDaysGermination, avgDaysHarvest);
        this.id = id;
        this.name = name;
        this.goodForTea = goodForTea;
    }
    public Herb(String name, String family, String description, String location, String ground, String sowOutdoors, String sowIndoors, String harvest, int depth, int distanceBetweenPlants, int distanceBetweenRows, int avgDaysGermination, int avgDaysHarvest, boolean goodForTea) {
        super(family, description, location, ground, sowOutdoors, sowIndoors, harvest, depth, distanceBetweenPlants, distanceBetweenRows, avgDaysGermination, avgDaysHarvest);
        this.name = name;
        this.goodForTea = goodForTea;
    }

    public int getId() {
        return id;
    }
    public boolean isGoodForTea() {
        return goodForTea;
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
    public void setGoodForTea(boolean goodForTea) {
        this.goodForTea = goodForTea;
    }
}
