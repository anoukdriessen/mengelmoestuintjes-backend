package nl.mengelmoestuintjes.gardening.model.tasks;

import nl.mengelmoestuintjes.gardening.model.users.Milestone;

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
    private boolean isDone;
    private LocalDate starting;
    private LocalDate dueDate;
    private long points;

    public Task() {}
    public Task(long id, TypeTask type, String title, String description, boolean isDone) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.description = description;
        this.isDone = isDone;
    }
    public Task(TypeTask type, String title, String description, LocalDate starting, LocalDate dueDate, long points) {
        this.title = title;
        this.description = description;
        this.isDone = false;
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
    public boolean isDone() {
        return isDone;
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

    public void setId(long id) {
        this.id = id;
    }
    public void setType(TypeTask type) {
        this.type = type;
    }
    public void setDone(boolean done) {
        isDone = done;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setIsDone(boolean done) {
        isDone = done;
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
                this.isDone);
    }
}
