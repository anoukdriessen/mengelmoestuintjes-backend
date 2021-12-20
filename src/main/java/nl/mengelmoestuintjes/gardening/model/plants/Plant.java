package nl.mengelmoestuintjes.gardening.model.plants;

import javax.persistence.*;

@Entity
@Table( name = "plants")
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Category type;
    private String name;
    private String family;              // de familie waar de plant bij hoort
    private String description;         // de beschrijving van de plant
    private String location;            // de standplaats van de plant
    private String ground;              // de grondbehoefte van de plant
    private Boolean forPreSow;          // geschikt voor voorzaaien
    private Boolean forSeedCollecting;  // geschikt voor het verzamelen van zaad
    private Boolean forPot;             // geschikt voor pot
    private Boolean forOutside;         // geschikt voor buiten
    private Boolean forIndoor;         // geschikt voor buiten
    private Boolean forGreenhouse;      // geschikt voor kas
    private Boolean forVerticalGarden;  // geschikt voor verticale tuin
    private String sowOutdoors;          // maanden waarin de plant buiten gezaaid kan worden
    private String sowIndoors;           // maanden waarin de plant binnen gezaaid kan worden
    private String harvest;              // maanden waarin de plant geoogst kan worden
    private boolean frostResistance;    // de winterhardheid van de plant
    private int waterRequirement;       // de waterbehoefte van de plant
    private int depth;                  // de zaaidiepte
    private int distanceBetweenPlants;  // de afstand tussen planten
    private int distanceBetweenRows;    // de afstand tussen rijen
    private int plantsPerMeter;         // planten per meter
    private int plantsPerSquare;        // planten per vierkante meter
    private int avgDaysGermination;     // gemiddelde tijd tot ontkiemen
    private int avgDaysHarvest;         // gemiddelde tijd tot oogst

    // TODO IMPLEMENT RELATION
//    ListOfPlants friends;   // vrienden van de plant (compagnion-planting)
//    ListOfEnemies enemies  // vijanden van de plant (compagnion-planting)

    public Plant() {}
    public Plant(int id, Category type, String name, String family, String description, String location, String ground, Boolean forPreSow, Boolean forSeedCollecting, Boolean forPot, Boolean forOutside, Boolean forIndoor, Boolean forGreenhouse, Boolean forVerticalGarden, String sowOutdoors, String sowIndoors, String harvest, boolean frostResistance, int waterRequirement, int depth, int distanceBetweenPlants, int distanceBetweenRows, int plantsPerMeter, int plantsPerSquare, int avgDaysGermination, int avgDaysHarvest) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.family = family;
        this.description = description;
        this.location = location;
        this.ground = ground;
        this.forPreSow = forPreSow;
        this.forSeedCollecting = forSeedCollecting;
        this.forPot = forPot;
        this.forOutside = forOutside;
        this.forIndoor = forIndoor;
        this.forGreenhouse = forGreenhouse;
        this.forVerticalGarden = forVerticalGarden;
        this.sowOutdoors = sowOutdoors;
        this.sowIndoors = sowIndoors;
        this.harvest = harvest;
        this.frostResistance = frostResistance;
        this.waterRequirement = waterRequirement;
        this.depth = depth;
        this.distanceBetweenPlants = distanceBetweenPlants;
        this.distanceBetweenRows = distanceBetweenRows;
        this.plantsPerMeter = plantsPerMeter;
        this.plantsPerSquare = plantsPerSquare;
        this.avgDaysGermination = avgDaysGermination;
        this.avgDaysHarvest = avgDaysHarvest;
    }
    public Plant(Category type, String name, String description ) {
        this.type = type;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getFamily() {
        return family;
    }
    public String getDescription() {
        return description;
    }
    public String getLocation() {
        return location;
    }
    public String getGround() {
        return ground;
    }
    public Boolean getForPreSow() {
        return forPreSow;
    }
    public Boolean getForSeedCollecting() {
        return forSeedCollecting;
    }
    public Boolean getForPot() {
        return forPot;
    }
    public Boolean getForOutside() {
        return forOutside;
    }
    public Boolean getForIndoor() {
        return forIndoor;
    }
    public Boolean getForGreenhouse() {
        return forGreenhouse;
    }
    public Boolean getForVerticalGarden() {
        return forVerticalGarden;
    }
    public String getSowOutdoors() {
        return sowOutdoors;
    }
    public String getSowIndoors() {
        return sowIndoors;
    }
    public String getHarvest() {
        return harvest;
    }
    public boolean getFrostResistance() {
        return frostResistance;
    }
    public int getWaterRequirement() {
        return waterRequirement;
    }
    public int getDepth() {
        return depth;
    }
    public int getDistanceBetweenPlants() {
        return distanceBetweenPlants;
    }
    public int getDistanceBetweenRows() {
        return distanceBetweenRows;
    }
    public int getPlantsPerMeter() {
        return plantsPerMeter;
    }
    public int getPlantsPerSquare() {
        return plantsPerSquare;
    }
    public int getAvgDaysGermination() {
        return avgDaysGermination;
    }
    public int getAvgDaysHarvest() {
        return avgDaysHarvest;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setType(Category type) {
        this.type = type;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setFamily(String family) {
        this.family = family;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setGround(String ground) {
        this.ground = ground;
    }
    public void setForPreSow(Boolean forPreSow) {
        this.forPreSow = forPreSow;
    }
    public void setForSeedCollecting(Boolean forSeedCollecting) {
        this.forSeedCollecting = forSeedCollecting;
    }
    public void setForPot(Boolean forPot) {
        this.forPot = forPot;
    }
    public void setForOutside(Boolean forOutside) {
        this.forOutside = forOutside;
    }
    public void setForIndoor(Boolean forIndoor) {
        this.forIndoor = forIndoor;
    }
    public void setForGreenhouse(Boolean forGreenhouse) {
        this.forGreenhouse = forGreenhouse;
    }
    public void setForVerticalGarden(Boolean forVerticalGarden) {
        this.forVerticalGarden = forVerticalGarden;
    }
    public void setSowOutdoors(String sowOutdoors) {
        this.sowOutdoors = sowOutdoors;
    }
    public void setSowIndoors(String sowIndoors) {
        this.sowIndoors = sowIndoors;
    }
    public void setHarvest(String harvest) {
        this.harvest = harvest;
    }
    public void setFrostResistance(boolean frostResistance) {
        this.frostResistance = frostResistance;
    }
    public void setWaterRequirement(int waterRequirement) {
        this.waterRequirement = waterRequirement;
    }
    public void setDepth(int depth) {
        this.depth = depth;
    }
    public void setDistanceBetweenPlants(int distanceBetweenPlants) {
        this.distanceBetweenPlants = distanceBetweenPlants;
    }
    public void setDistanceBetweenRows(int distanceBetweenRows) {
        this.distanceBetweenRows = distanceBetweenRows;
    }
    public void setPlantsPerMeter(int plantsPerMeter) {
        this.plantsPerMeter = plantsPerMeter;
    }
    public void setPlantsPerSquare(int plantsPerSquare) {
        this.plantsPerSquare = plantsPerSquare;
    }
    public void setAvgDaysGermination(int avgDaysGermination) {
        this.avgDaysGermination = avgDaysGermination;
    }
    public void setAvgDaysHarvest(int avgDaysHarvest) {
        this.avgDaysHarvest = avgDaysHarvest;
    }

}

