package nl.mengelmoestuintjes.gardening.model.tasks;
import javax.persistence.*;

@Entity
@Table(name = "tasks_garden")
/* GardeningTask bestaat uit:
   - title
   - beschrijving
   - uitgevoerd of niet
   - punten (xp)
   - maand waarin die uitgevoerd moet worden (optioneel)

   moderators kunnen GardeningTasks aanmaken in het moderator dashboard
   gebruikers kunnen GardeningTasks uitvoeren
**/
public class GardeningTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private boolean done;
    private long points;
    private int month;

    public GardeningTask() {}
    public GardeningTask(int id, String title, String description, boolean done, long points, int month) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.done = done;
        this.points = points;
        this.month = month;
    }
    public GardeningTask(String title, String description, boolean done, long points, int month) {
        this.title = title;
        this.description = description;
        this.done = done;
        this.points = points;
        this.month = month;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return this.title;
    }
    public String getDescription() {
        return this.description;
    }
    public boolean isDone() {
        return this.done;
    }
    public long getPoints() {
        return points;
    }
    public int getMonth() {
        return month;
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
    public void setPoints(long points) {
        this.points = points;
    }
    public void setMonth(int month) {
        this.month = month;
    }
}
