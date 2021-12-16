package nl.mengelmoestuintjes.gardening.model.tasks;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tasks_todo")

/* ToDoTask die bestaat uit:
    - title
    - beschrijving
    - uitgevoerd of niet
    - datum van uitvoeren
*/
public class ToDoTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private boolean done;
    private Date dueDate;

    public ToDoTask() {}
    public ToDoTask(int id, String title, String description, boolean done, Date dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.done = done;
        this.dueDate = dueDate;
    }
    public ToDoTask(String title, String description, boolean done, Date dueDate) {
        this.title = title;
        this.description = description;
        this.done = done;
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public boolean isDone() {
        return done;
    }
    public Date getDueDate() {
        return dueDate;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setDone(boolean done) {
       this.done = done;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
