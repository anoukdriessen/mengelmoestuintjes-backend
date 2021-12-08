package nl.mengelmoestuintjes.gardening.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "plants")
public class Flower extends Plant {
//    int[] bloom;                // maanden waarin de plant bloeit

    // empty constructor
    public Flower() {
        super();
    }
}
