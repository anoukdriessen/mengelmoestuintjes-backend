package nl.mengelmoestuintjes.gardening.model.plants;

public class Vegetable extends Plant {

    public Vegetable() {
        super();
    }
    public Vegetable(int id, Category category, String name, String family, String description, String location, String ground, Boolean forPreSow, Boolean forSeedCollecting, Boolean forPot, Boolean forOutside, Boolean forIndoor, Boolean forGreenhouse, Boolean forVerticalGarden, String sowOutdoors, String sowIndoors, String harvest, boolean frostResistance, int waterRequirement, int depth, int distanceBetweenPlants, int distanceBetweenRows, int plantsPerMeter, int plantsPerSquare, int avgDaysGermination, int avgDaysHarvest) {
        super(id, category, name, family, description, location,ground,forPreSow, forSeedCollecting, forPot, forOutside,forIndoor, forGreenhouse, forVerticalGarden, sowOutdoors, sowIndoors, harvest, frostResistance, waterRequirement, depth, distanceBetweenPlants, distanceBetweenRows, plantsPerMeter, plantsPerSquare, avgDaysGermination, avgDaysHarvest);
    }
    public Vegetable(Category type, String name, String description) {
        super( type, name, description );
    }

    public int getId() {
        return super.getId();
    }
    public String getFamily() {
        return super.getFamily();
    }
    public String getDescription() {
        return super.getDescription();
    }
    public String getLocation() {
        return super.getLocation();
    }
    public String getGround() {
        return super.getGround();
    }
    public Boolean getForPreSow() {
        return super.getForPreSow();
    }
    public Boolean getForSeedCollecting() {
        return super.getForSeedCollecting();
    }
    public Boolean getForPot() {
        return super.getForPot();
    }
    public Boolean getForOutside() {
        return super.getForOutside();
    }
    public Boolean getForIndoor() {
        return super.getForIndoor();
    }
    public Boolean getForGreenhouse() {
        return super.getForGreenhouse();
    }
    public Boolean getForVerticalGarden() {
        return super.getForVerticalGarden();
    }
    public String getSowOutdoors() {
        return super.getSowOutdoors();
    }
    public String getSowIndoors() {
        return super.getSowIndoors();
    }
    public String getHarvest() {
        return super.getHarvest();
    }
    public boolean getFrostResistance() {
        return super.getFrostResistance();
    }
    public int getWaterRequirement() {
        return super.getWaterRequirement();
    }
    public int getDepth() {
        return super.getDepth();
    }
    public int getDistanceBetweenPlants() {
        return super.getDistanceBetweenPlants();
    }
    public int getDistanceBetweenRows() {
        return super.getDistanceBetweenRows();
    }
    public int getPlantsPerMeter() {
        return super.getPlantsPerMeter();
    }
    public int getPlantsPerSquare() {
        return super.getPlantsPerSquare();
    }
    public int getAvgDaysGermination() {
        return super.getAvgDaysGermination();
    }
    public int getAvgDaysHarvest() {
        return super.getAvgDaysHarvest();
    }
    public void setId( int id ) { super.setId( id ); }
    public void setType(Category type) {
        super.setType(type);
    }
    public void setName(String name) {
        super.setName(name);
    }
    public void setFamily(String family) {
        super.setFamily(family);
    }
    public void setDescription(String description) {
        super.setDescription(description);
    }
    public void setLocation(String location) {
        super.setLocation(location);
    }
    public void setGround(String ground) {
        super.setGround(ground);
    }
    public void setForPreSow(Boolean forPreSow) {
        super.setForPreSow(forPreSow);
    }
    public void setForSeedCollecting(Boolean forSeedCollecting) {
        super.setForSeedCollecting(forSeedCollecting);
    }
    public void setForPot(Boolean forPot) {
        super.setForPot(forPot);
    }
    public void setForOutside(Boolean forOutside) {
        super.setForOutside(forOutside);
    }
    public void setForIndoor(Boolean forIndoor) {
        super.setForIndoor(forIndoor);
    }
    public void setForGreenhouse(Boolean forGreenhouse) {
        super.setForGreenhouse(forGreenhouse);
    }
    public void setForVerticalGarden(Boolean forVerticalGarden) {
        super.setForVerticalGarden(forVerticalGarden);
    }
    public void setSowOutdoors(String sowOutdoors) {
        super.setSowOutdoors(sowOutdoors);
    }
    public void setSowIndoors(String sowIndoors) {
        super.setSowIndoors(sowIndoors);
    }
    public void setHarvest(String harvest) {
        super.setHarvest(harvest);
    }
    public void setFrostResistance(boolean frostResistance) {
        super.setFrostResistance(frostResistance);
    }
    public void setWaterRequirement(int waterRequirement) {
        super.setWaterRequirement(waterRequirement);
    }
    public void setDepth(int depth) {
        super.setDepth(depth);
    }
    public void setDistanceBetweenPlants(int distanceBetweenPlants) {
        super.setDistanceBetweenPlants(distanceBetweenPlants);
    }
    public void setDistanceBetweenRows(int distanceBetweenRows) {
        super.setDistanceBetweenRows(distanceBetweenRows);
    }
    public void setPlantsPerMeter(int plantsPerMeter) {
        super.setPlantsPerMeter(plantsPerMeter);
    }
    public void setPlantsPerSquare(int plantsPerSquare) {
        super.setPlantsPerSquare(plantsPerSquare);
    }
    public void setAvgDaysGermination(int avgDaysGermination) {
        super.setAvgDaysGermination(avgDaysGermination);
    }
    public void setAvgDaysHarvest(int avgDaysHarvest) {
        super.setAvgDaysHarvest(avgDaysHarvest);
    }
}
