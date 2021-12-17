//package nl.mengelmoestuintjes.gardening.model.tasks;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import nl.mengelmoestuintjes.gardening.model.users.User;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Entity
//@Table(name = "tasks_todo")
//public class ToDoTask extends Task {
//
//    private boolean done;
//    private Date dueDate;
//
//    @JsonIgnoreProperties("tasks_todo")
//    @ManyToOne
//    @JoinColumn( name = "user_id", referencedColumnName = "id")
//    private User owner;
//
//    public ToDoTask() {}
//    public ToDoTask(int id, String title, String description, boolean done, Date dueDate) {
//        this.id = id;
//        this.title = title;
//        this.description = description;
//        this.done = done;
//        this.dueDate = dueDate;
//    }
//    public ToDoTask(String title, String description, boolean done, Date dueDate) {
//        this.title = title;
//        this.description = description;
//        this.done = done;
//        this.dueDate = dueDate;
//    }
//
//    public int getId() {
//        return id;
//    }
//    public String getTitle() {
//        return title;
//    }
//    public String getDescription() {
//        return description;
//    }
//    public boolean isDone() {
//        return done;
//    }
//    public Date getDueDate() {
//        return dueDate;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//    public void setTitle(String title) {
//        this.title = title;
//    }
//    public void setDescription(String description) {
//        this.description = description;
//    }
//    public void setDone(boolean done) {
//       this.done = done;
//    }
//    public void setDueDate(Date dueDate) {
//        this.dueDate = dueDate;
//    }
//
//    @Override
//    public String toString() {
//        return String.format("%s ) %s \n %s \n %s, %s",
//                this.id, this.title,
//                this.description,
//                this.done, this.dueDate );
//    }
//}
