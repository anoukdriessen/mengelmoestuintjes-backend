package nl.mengelmoestuintjes.gardening.model.plants;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "plants")
public class Fruit extends Plant {

    // empty constructor
    public Fruit() {
        super();
    }

}
