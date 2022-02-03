package nl.mengelmoestuintjes.gardening.model.garden.plants;

import lombok.Data;
import nl.mengelmoestuintjes.gardening.model.Months;
import nl.mengelmoestuintjes.gardening.model.garden.Ground;

import javax.persistence.*;

import static nl.mengelmoestuintjes.gardening.model.garden.plants.Level.LOW;

@Entity
@Table( name = "plant_details" )
@Data
public class PlantDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String official;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Level waterRequirement;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Level sunRequirement;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Level survivingWinter;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Ground groundRequirement;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Months startSowInside;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Months endSowInside;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Months startSowOutside;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Months endSowOutside;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Months startHarvest;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Months endHarvest;

    @Column(nullable = true)
    private boolean preSow;
    @Column(nullable = true)
    private boolean forPot;
    @Column(nullable = true)
    private boolean forOutside;
    @Column(nullable = true)
    private boolean forGreenhouse;

    private int sowingDepth;
    private int distanceBetweenX;
    private int distanceBetweenY;
    private int avgDaysTillSprout;
    private int avgDaysTillHarvest;

    public void setToEmpty() {
        this.setOfficial("");
        this.setWaterRequirement(LOW);
        this.setSunRequirement(LOW);
        this.setSurvivingWinter(LOW);
        this.setGroundRequirement(Ground.UNKNOWN);

        this.setStartSowInside(Months.NONE);
        this.setEndSowInside(Months.NONE);

        this.setStartSowOutside(Months.NONE);
        this.setEndSowOutside(Months.NONE);

        this.setStartHarvest(Months.NONE);
        this.setEndHarvest(Months.NONE);

        this.setSowingDepth(0);
        this.setDistanceBetweenX(0);
        this.setDistanceBetweenY(0);
        this.setAvgDaysTillSprout(0);
        this.setAvgDaysTillHarvest(0);

//        private boolean preSow;
//        private boolean forPot;
//        private boolean forOutside;
//        private boolean forGreenhouse;
    }

}
