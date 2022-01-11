package nl.mengelmoestuintjes.gardening.dto.request;

import lombok.Data;
import nl.mengelmoestuintjes.gardening.model.Task;
import nl.mengelmoestuintjes.gardening.model.TaskType;
import nl.mengelmoestuintjes.gardening.model.User;
import nl.mengelmoestuintjes.gardening.model.garden.Field;

import java.util.ArrayList;
import java.util.List;

@Data
public class GardenRequest {
    private long id;
    private String name;
    private List<User> owners = new ArrayList<>();
    private String size;
    private int x;
    private int y;
    private List<Task> tasks = new ArrayList<>();
    private ArrayList<Field> fields = new ArrayList<>();

    public boolean hasOwner(User user){
        for (User u : this.getOwners()) {
            if (u.equals(user)) return true;
        }
        return false;
    }
    public void addOwner(User user) {
        if (!hasOwner(user)) this.owners.add(user);
    }
    public void removeOwner(User user) {
        if (hasOwner(user)) this.owners.remove(user);
    }
    public void setTasks() {
        tasks.clear(); // make empty
        for (User u : this.owners) {
            tasks.addAll(u.getTasksByType(TaskType.GARDENING));
        }
    }
}
