package nl.mengelmoestuintjes.gardening.model.garden;

import nl.mengelmoestuintjes.gardening.model.users.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name= "garden_owner")
public class GardenOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn( name = "owner_id")
    User user;

    @ManyToOne
    @JoinColumn( name = "garden_id")
    Garden garden;

    LocalDateTime registeredAt;

    long level;

    public int getId() {
        return id;
    }
    public User getUser() {
        return user;
    }
    public Garden getGarden() {
        return garden;
    }
    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }
    public long getLevel() {
        return level;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setGarden(Garden garden) {
        this.garden = garden;
    }
    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }
    public void setLevel(long level) {
        this.level = level;
    }

}
