package nl.mengelmoestuintjes.gardening.model.plants;

import javax.persistence.*;

@Entity
@Table(name = "plants")
public class Plant {
    // atributen
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;                // de naam van de plant
    @Column(name = "family")
    String family;              // de familie waar de plant bij hoort
    @Column(name = "description")
    String description;         // de beschrijving van de plant
    @Column(name = "location")
    String location;            // de standplaats van de plant
    @Column(name = "ground")
    String ground;              // de grondbehoefte van de plant
    @Column(name = "pre_sow")
    Boolean forPreSow;          // geschikt voor voorzaaien
    @Column(name = "seed_collecting")
    Boolean forSeedCollecting;  // geschikt voor het verzamelen van zaad
    @Column(name = "for_pot")
    Boolean forPot;             // geschikt voor pot
    @Column(name = "for_outside")
    Boolean forOutside;         // geschikt voor buiten
    @Column(name = "for_greenhouse")
    Boolean forGreenhouse;      // geschikt voor kas
    @Column(name = "for_vertical")
    Boolean forVerticalGarden;  // geschikt voor verticale tuin

//    int[] sowIndoors;           // maanden waarin de plant binnen gezaaid kan worden
//    int[] sowOutdoors;          // maanden waarin de plant buiten gezaaid kan worden
//    int[] harvest;              // maanden waarin de plant geoogst kan worden

    @Column(name = "frost_resistance")
    int frostResistance;        // de winterhardheid van de plant
    @Column(name = "water_requirement")
    int waterRequirement;       // de waterbehoefte van de plant
    @Column(name = "depth")
    int depth;                  // de zaaidiepte
    @Column(name = "between_plants")
    int distanceBetweenPlants;  // de afstand tussen planten
    @Column(name = "between_rows")
    int distanceBetweenRows;    // de afstand tussen rijen
//    int plantsPerMeter;         // planten per meter
//    int plantsPerSquare;        // planten per vierkante meter
    @Column(name = "germinatation_days")
    int avgDaysGermination;     // gemiddelde tijd tot ontkiemen
    @Column(name = "harvest_days")
    int avgDaysHarvest;         // gemiddelde tijd tot oogst

//    ArrayList<Plant> friends;   // vrienden van de plant (compagnion-planting)
//    ArrayList<Plant> enemies;   // vijanden van de plant (compagnion-planting)

    // empty constructor
    public Plant() {
    }

    // getters & setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGround() {
        return ground;
    }

    public void setGround(String ground) {
        this.ground = ground;
    }

    public Boolean getForPreSow() {
        return forPreSow;
    }

    public void setForPreSow(Boolean forPreSow) {
        this.forPreSow = forPreSow;
    }

    public Boolean getForSeedCollecting() {
        return forSeedCollecting;
    }

    public void setForSeedCollecting(Boolean forSeedCollecting) {
        this.forSeedCollecting = forSeedCollecting;
    }

    public Boolean getForPot() {
        return forPot;
    }

    public void setForPot(Boolean forPot) {
        this.forPot = forPot;
    }

    public Boolean getForOutside() {
        return forOutside;
    }

    public void setForOutside(Boolean forOutside) {
        this.forOutside = forOutside;
    }

    public Boolean getForGreenhouse() {
        return forGreenhouse;
    }

    public void setForGreenhouse(Boolean forGreenhouse) {
        this.forGreenhouse = forGreenhouse;
    }

    public Boolean getForVerticalGarden() {
        return forVerticalGarden;
    }

    public void setForVerticalGarden(Boolean forVerticalGarden) {
        this.forVerticalGarden = forVerticalGarden;
    }

//    public int[] getSowIndoors() {
//        return sowIndoors;
//    }
//
//    public void setSowIndoors(int[] sowIndoors) {
//        this.sowIndoors = sowIndoors;
//    }
//
//    public int[] getSowOutdoors() {
//        return sowOutdoors;
//    }

//    public void setSowOutdoors(int[] sowOutdoors) {
//        this.sowOutdoors = sowOutdoors;
//    }
//
//    public int[] getHarvest() {
//        return harvest;
//    }
//
//    public void setHarvest(int[] harvest) {
//        this.harvest = harvest;
//    }

    public int getFrostResistance() {
        return frostResistance;
    }

    public void setFrostResistance(int frostResistance) {
        this.frostResistance = frostResistance;
    }

    public int getWaterRequirement() {
        return waterRequirement;
    }

    public void setWaterRequirement(int waterRequirement) {
        this.waterRequirement = waterRequirement;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getDistanceBetweenPlants() {
        return distanceBetweenPlants;
    }

    public void setDistanceBetweenPlants(int distanceBetweenPlants) {
        this.distanceBetweenPlants = distanceBetweenPlants;
    }

    public int getDistanceBetweenRows() {
        return distanceBetweenRows;
    }

    public void setDistanceBetweenRows(int distanceBetweenRows) {
        this.distanceBetweenRows = distanceBetweenRows;
    }

//    public int getPlantsPerMeter() {
//        return plantsPerMeter;
//    }
//
//    public void setPlantsPerMeter(int plantsPerMeter) {
//        this.plantsPerMeter = plantsPerMeter;
//    }
//
//    public int getPlantsPerSquare() {
//        return plantsPerSquare;
//    }
//
//    public void setPlantsPerSquare(int plantsPerSquare) {
//        this.plantsPerSquare = plantsPerSquare;
//    }

    public int getAvgDaysGermination() {
        return avgDaysGermination;
    }

    public void setAvgDaysGermination(int avgDaysGermination) {
        this.avgDaysGermination = avgDaysGermination;
    }

    public int getAvgDaysHarvest() {
        return avgDaysHarvest;
    }

    public void setAvgDaysHarvest(int avgDaysHarvest) {
        this.avgDaysHarvest = avgDaysHarvest;
    }

//    public ArrayList<Plant> getFriends() {
//        return friends;
//    }
//
//    public void setFriends(ArrayList<Plant> friends) {
//        this.friends = friends;
//    }
//
//    public ArrayList<Plant> getEnemies() {
//        return enemies;
//    }
//
//    public void setEnemies(ArrayList<Plant> enemies) {
//        this.enemies = enemies;
//    }
}
