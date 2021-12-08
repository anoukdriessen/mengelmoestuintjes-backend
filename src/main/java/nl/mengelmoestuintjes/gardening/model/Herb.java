package nl.mengelmoestuintjes.gardening.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "plants")
public class Herb extends Plant {

    // empty constructor
    public Herb() {
        super();
    }

}
