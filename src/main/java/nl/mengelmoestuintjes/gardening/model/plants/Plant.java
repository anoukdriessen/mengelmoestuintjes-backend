package nl.mengelmoestuintjes.gardening.model.plants;

import javax.persistence.*;

@Entity
@Table( name = "plants" )
public class Plant {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    private Category category;
    private String name;
    private String description;

    private Location location;            // de standplaats van de plant

    /*
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
    * */


    // TODO IMPLEMENT RELATION
//    ListOfPlants friends;   // vrienden van de plant (compagnion-planting)
//    ListOfEnemies enemies  // vijanden van de plant (compagnion-planting)

    public Plant(){}

    // TODO FULL CONSTRUCTOR

    public Plant(Category category, String name, String description, Location location ) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.location = location;
    }

    public long getId() {
        return id;
    }
    public Category getCategory() {
        return category;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public Location getLocation() {
        return location;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return String.format("%s ) %s \n %s \n %s", this.id, this.category, this.name, this.description);
    }
}
