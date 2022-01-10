//package nl.mengelmoestuintjes.gardening.model.plants;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import nl.mengelmoestuintjes.gardening.model.User;
//import org.springframework.lang.Nullable;
//
//import javax.persistence.*;
//
//@Entity
//@Table( name = "plants" )
//public class Plant {
//    @Id
//    @GeneratedValue( strategy = GenerationType.IDENTITY )
//    private long id;
//
//    private Category category;
//    private String name;
//    private String description;
//
//    @Column(columnDefinition = "integer default 0")
//    private Location location;            // de standplaats van de plant
//    @Column(columnDefinition = "varchar(255) default 'outdoor'")
//    private String outdoors;              // maanden voor buiten zaaien
//    @Column(columnDefinition = "varchar(255) default 'indoor'")
//    private String indoors;               // maanden voor binnen zaaien
//    @Column(columnDefinition = "varchar(255) default 'harvest'")
//    private String harvest;               // maanden voor oogst
//    @Column(columnDefinition = "integer default 0")
//    private Requirement frostResistance;  // is de plant vorstbestendig
//    @Column(columnDefinition = "integer default 0")
//    private Requirement waterRequirement; // de waterbehoefte van de plant
//    @Column(columnDefinition = "boolean default false")
//    private boolean forPreSow;            // geschikt voor voorzaaien
//    @Column(columnDefinition = "boolean default false")
//    private boolean forSeedCollecting;    // geschikt voor het verzamelen van zaad
//    @Column(columnDefinition = "boolean default false")
//    private boolean forPot;               // geschikt voor pot
//    @Column(columnDefinition = "boolean default false")
//    private boolean forOutdoors;          // geschikt voor buiten
//    @Column(columnDefinition = "boolean default false")
//    private boolean forIndoors;           // geschikt voor binnen
//    @Column(columnDefinition = "boolean default false")
//    private boolean forGreenhouse;        // geschikt voor in kas
//    @Column(columnDefinition = "boolean default false")
//    private boolean forVertical;          // geschikt voor verticale tuin
//    @Column(columnDefinition = "integer default 0")
//    private int depth;                  // de zaaidiepte
//    @Column(columnDefinition = "integer default 0")
//    private int distanceBetweenPlants;  // de afstand tussen planten
//    @Column(columnDefinition = "integer default 0")
//    private int distanceBetweenRows;    // de afstand tussen rijen
//    @Column(columnDefinition = "integer default 0")
//    private int avgDaysGermination;     // gemiddelde tijd tot ontkiemen
//    @Column(columnDefinition = "integer default 0")
//    private int avgDaysHarvest;         // gemiddelde tijd tot oogst
//
//    @Column(columnDefinition = "boolean default false")
//    private boolean visible;
//    /*
//    private int plantsPerMeter;         // planten per meter
//    private int plantsPerSquare;        // planten per vierkante meter
//    * */
//
//
//    // TODO IMPLEMENT RELATION
////    ListOfPlants friends;   // vrienden van de plant (compagnion-planting)
////    ListOfEnemies enemies  // vijanden van de plant (compagnion-planting)
//
//    @JsonIgnoreProperties("plants")
//    @ManyToOne
//    @JoinColumn( name = "user_id", referencedColumnName = "id")
//    private User owner;
//
//    public Plant(){}
//    public Plant(long id, Category category, String name, String description, Location location, String outdoors, String indoors, String harvest, Requirement frostResistance, Requirement waterRequirement, boolean forPreSow, boolean forSeedCollecting, boolean forPot, boolean forOutdoors, boolean forIndoors, boolean forGreenhouse, boolean forVertical, int depth, int distanceBetweenPlants, int distanceBetweenRows, int avgDaysGermination, int avgDaysHarvest, boolean visible) {
//        this.id = id;
//        this.category = category;
//        this.name = name;
//        this.description = description;
//        this.location = location;
//        this.outdoors = outdoors;
//        this.indoors = indoors;
//        this.harvest = harvest;
//        this.frostResistance = frostResistance;
//        this.waterRequirement = waterRequirement;
//        this.forPreSow = forPreSow;
//        this.forSeedCollecting = forSeedCollecting;
//        this.forPot = forPot;
//        this.forOutdoors = forOutdoors;
//        this.forIndoors = forIndoors;
//        this.forGreenhouse = forGreenhouse;
//        this.forVertical = forVertical;
//        this.depth = depth;
//        this.distanceBetweenPlants = distanceBetweenPlants;
//        this.distanceBetweenRows = distanceBetweenRows;
//        this.avgDaysGermination = avgDaysGermination;
//        this.avgDaysHarvest = avgDaysHarvest;
//        this.visible = visible;
//    }
//    public Plant(Category category, String name, String description) {
//        this.category = category;
//        this.name = name;
//        this.description = description;
//        this.visible = false;
//
//        this.location = Location.BOTH;
//        this.frostResistance = Requirement.LOW;
//        this.waterRequirement = Requirement.LOW;
//        this.outdoors = "";
//        this.indoors = "";
//        this.harvest = "";
//        this.forPreSow = false;
//        this.forSeedCollecting = false;
//        this.forPot = false;
//        this.forOutdoors = false;
//        this.forIndoors = false;
//        this.forGreenhouse = false;
//        this.forVertical = false;
//
//        this.depth = 0;
//        this.distanceBetweenPlants = 0;
//        this.distanceBetweenRows = 0;
//        this.avgDaysGermination = 0;
//        this.avgDaysHarvest = 0;
//    }
//
//    public long getId() {
//        return id;
//    }
//    public Category getCategory() {
//        return category;
//    }
//    public String getName() {
//        return name;
//    }
//    public String getDescription() {
//        return description;
//    }
//    public Location getLocation() {
//        return location;
//    }
//    public String getOutdoors() {
//        return outdoors;
//    }
//    public String getIndoors() {
//        return indoors;
//    }
//    public String getHarvest() {
//        return harvest;
//    }
//    public Requirement getFrostResistance() {
//        return frostResistance;
//    }
//    public Requirement getWaterRequirement() {
//        return waterRequirement;
//    }
//    public boolean isForPreSow() {
//        return forPreSow;
//    }
//    public boolean isForSeedCollecting() {
//        return forSeedCollecting;
//    }
//    public boolean isForPot() {
//        return forPot;
//    }
//    public boolean isForOutdoors() {
//        return forOutdoors;
//    }
//    public boolean isForIndoors() {
//        return forIndoors;
//    }
//    public boolean isForGreenhouse() {
//        return forGreenhouse;
//    }
//    public boolean isForVertical() {
//        return forVertical;
//    }
//    public int getDepth() {
//        return depth;
//    }
//    public int getDistanceBetweenPlants() {
//        return distanceBetweenPlants;
//    }
//    public int getDistanceBetweenRows() {
//        return distanceBetweenRows;
//    }
//    public int getAvgDaysGermination() {
//        return avgDaysGermination;
//    }
//    public int getAvgDaysHarvest() {
//        return avgDaysHarvest;
//    }
//    public boolean isVisible() {
//        return visible;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//    public void setCategory(Category category) {
//        this.category = category;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//    public void setDescription(String description) {
//        this.description = description;
//    }
//    public void setLocation(Location location) {
//        this.location = location;
//    }
//    public void setOutdoors(String outdoors) {
//        this.outdoors = outdoors;
//    }
//    public void setIndoors(String indoors) {
//        this.indoors = indoors;
//    }
//    public void setHarvest(String harvest) {
//        this.harvest = harvest;
//    }
//    public void setFrostResistance(Requirement frostResistance) {
//        this.frostResistance = frostResistance;
//    }
//    public void setWaterRequirement(Requirement waterRequirement) {
//        this.waterRequirement = waterRequirement;
//    }
//    public void setForPreSow(boolean forPreSow) {
//        this.forPreSow = forPreSow;
//    }
//    public void setForSeedCollecting(boolean forSeedCollecting) {
//        this.forSeedCollecting = forSeedCollecting;
//    }
//    public void setForPot(boolean forPot) {
//        this.forPot = forPot;
//    }
//    public void setForOutdoors(boolean forOutdoors) {
//        this.forOutdoors = forOutdoors;
//    }
//    public void setForIndoors(boolean forIndoors) {
//        this.forIndoors = forIndoors;
//    }
//    public void setForGreenhouse(boolean forGreenhouse) {
//        this.forGreenhouse = forGreenhouse;
//    }
//    public void setForVertical(boolean forVertical) {
//        this.forVertical = forVertical;
//    }
//    public void setDepth(int depth) {
//        this.depth = depth;
//    }
//    public void setDistanceBetweenPlants(int distanceBetweenPlants) {
//        this.distanceBetweenPlants = distanceBetweenPlants;
//    }
//    public void setDistanceBetweenRows(int distanceBetweenRows) {
//        this.distanceBetweenRows = distanceBetweenRows;
//    }
//    public void setAvgDaysGermination(int avgDaysGermination) {
//        this.avgDaysGermination = avgDaysGermination;
//    }
//    public void setAvgDaysHarvest(int avgDaysHarvest) {
//        this.avgDaysHarvest = avgDaysHarvest;
//    }
//    public void setVisible(boolean visible) {
//        this.visible = visible;
//    }
//
//    @Override
//    public String toString() {
//        return String.format("%s ) %s \n %s \n %s", this.id, this.category, this.name, this.description);
//    }
//}
