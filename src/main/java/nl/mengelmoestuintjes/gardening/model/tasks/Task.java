package nl.mengelmoestuintjes.gardening.model.tasks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import nl.mengelmoestuintjes.gardening.model.users.Milestone;
import nl.mengelmoestuintjes.gardening.model.users.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private TypeTask type;
    private String title;
    private String description;
    private boolean done;
    private LocalDate starting;
    private LocalDate dueDate;
    private long points;

    public Task() {}
    public Task(long id, TypeTask type, String title, String description, boolean done) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.description = description;
        this.done = done;
    }
    public Task(TypeTask type, String title, String description, LocalDate starting, LocalDate dueDate, long points) {
        this.title = title;
        this.description = description;
        this.done = false;
        this.type = type;
        switch ( type ) {
            case TODO:
                this.dueDate = dueDate;
                break;
            case SEASONAL:
            case GARDENING:
                this.starting = starting;
                this.dueDate = dueDate;
                this.points = points;
                break;
        }
    }

    @JsonIgnoreProperties("tasks")
    @ManyToOne
    @JoinColumn( name = "user_id", referencedColumnName = "id")
    private User owner;

    public long getId() {
        return id;
    }
    public TypeTask getType() {
        return type;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public boolean getIsDone() {
        return done;
    }
    public LocalDate getStarting() {
        return starting;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public long getPoints() {
        return points;
    }
    public User getOwner() {
        return owner;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setType(TypeTask type) {
        this.type = type;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setIsDone(boolean done) {
        done = done;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setStarting(LocalDate starting) {
        this.starting = starting;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return String.format("%s ) %s, %s, \n %s, %s",
                this.id, this.type, this.title,
                this.description,
                this.done);
    }
}
