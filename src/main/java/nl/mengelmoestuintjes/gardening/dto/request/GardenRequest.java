package nl.mengelmoestuintjes.gardening.dto.request;

import lombok.Data;
import nl.mengelmoestuintjes.gardening.controller.exceptions.BadRequestException;
import nl.mengelmoestuintjes.gardening.model.Post;
import nl.mengelmoestuintjes.gardening.model.User;
import nl.mengelmoestuintjes.gardening.model.garden.Field;
import nl.mengelmoestuintjes.gardening.model.garden.Garden;

import java.util.ArrayList;
import java.util.List;

@Data
public class GardenRequest {
    private long id;
    private String name;
    private int x;
    private int y;
    private String size;
    private List<User> owners = new ArrayList<>();
    private List<Post> posts = new ArrayList<>();
    private ArrayList<Field> fields = new ArrayList<>();

    public void setName(String name) {
        if(!name.isEmpty() || !name.isBlank()) {
            this.name = name;
        } else {
            throw new BadRequestException("Name cannot be empty");
        }
    }

    public int calculateSize(int x, int y) {
        return x * y;
    }
    public void setSize(int x, int y) {
        this.x = x;
        this.y = y;
        this.size = "" + (calculateSize(x, y));
    }
    public boolean hasOwner(User user){
        for (User u : this.getOwners()) {
            if (u.equals(user)) return true;
        }
        return false;
    }
    public void addOwner(User user) {
        if (!hasOwner(user)) this.owners.add(user);
    }

    public Garden toGarden() {
        Garden newGarden = new Garden();

        newGarden.setId(this.id);
        newGarden.setName(this.name);
        newGarden.setX(this.x);
        newGarden.setY(this.y);
        newGarden.setSize(this.size);
        newGarden.setFields(this.fields);
        newGarden.setOwners(this.owners);
        newGarden.setPosts(this.posts);

        return newGarden;
    }
}
