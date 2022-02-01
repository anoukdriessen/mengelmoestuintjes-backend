package nl.mengelmoestuintjes.gardening.service;

import nl.mengelmoestuintjes.gardening.controller.exceptions.BadRequestException;
import nl.mengelmoestuintjes.gardening.controller.exceptions.GardenNotFoundException;
import nl.mengelmoestuintjes.gardening.dto.request.GardenRequest;
import nl.mengelmoestuintjes.gardening.dto.request.PostRequest;
import nl.mengelmoestuintjes.gardening.dto.response.GardenResponse;
import nl.mengelmoestuintjes.gardening.dto.response.UserResponse;
import nl.mengelmoestuintjes.gardening.model.Post;
import nl.mengelmoestuintjes.gardening.model.PostCategory;
import nl.mengelmoestuintjes.gardening.model.Task;
import nl.mengelmoestuintjes.gardening.model.User;
import nl.mengelmoestuintjes.gardening.model.garden.Field;
import nl.mengelmoestuintjes.gardening.model.garden.Garden;
import nl.mengelmoestuintjes.gardening.repository.GardenRepository;
import nl.mengelmoestuintjes.gardening.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class GardenService {

    private final GardenRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public GardenService(GardenRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    // CREATE
    public Garden create(GardenRequest toAdd) {
        Garden newGarden = new Garden();

        try {
            newGarden.setId(toAdd.getId());
            newGarden.setName(toAdd.getName());

            newGarden.setX(toAdd.getX());
            newGarden.setY(toAdd.getY());
            newGarden.setSize(newGarden.getX(), newGarden.getY());
            newGarden.setFields(toAdd.getFields());

            newGarden.setOwners(toAdd.getOwners());
            newGarden.setPosts(toAdd.getPosts());
            if (!newGarden.getOwners().isEmpty()) newGarden.setTasks();

            return repository.save(newGarden);
        } catch (Exception e) {
            throw new BadRequestException("cannot create garden");
        }

    }

    // READ
    public Iterable<GardenResponse> getAll() {
        Iterable<Garden> all = repository.findAll();
        ArrayList<GardenResponse> responses = new ArrayList<>();

        all.forEach(garden -> {
            responses.add(new GardenResponse(
                    garden.getId(),
                    garden.getName(),
                    garden.getSize(),
                    garden.getNumberOfTasks(),
                    garden.getPosts(),
                    garden.getFields(),
                    garden.getOwners()));
        });
        return responses;
    }
    public Garden getGarden(long id) {
        Optional<Garden> toFind = repository.findById(id);
        boolean gardenFound = toFind.isPresent();
        if (gardenFound) {
            return (Garden) toFind.get();
        } else {
            throw new GardenNotFoundException(id);
        }
    }
    public ArrayList<GardenResponse> findGardensByOwnersEquals(User user){
        ArrayList<GardenResponse> result = new ArrayList<>();
        Iterable<Garden> all = repository.findAll();
        for (Garden g : all) {
            // check if user is owner of garden
            for (UserResponse u : g.getOwners()) {
                if (u.getUsername().equals(user.getUsername())) {
                    GardenResponse thisGarden = new GardenResponse(
                            g.getId(),
                            g.getName(),
                            g.getSize(),
                            g.getNumberOfTasks(),
                            g.getPosts(),
                            g.getFields(),
                            g.getOwners()
                    );
                    result.add(thisGarden);
                }
            }
        }
        return result;
    }
    public Iterable<UserResponse> getUsers(long id) {
        Garden garden = getGarden(id);
        return garden.getOwners();
    }
    public Iterable<Task> getTasks(long id) {
        Garden garden = getGarden(id);
        return garden.getTasks();
    }
    public Iterable<Post> getPosts(long id) {
        Garden garden = getGarden(id);
        return garden.getPosts();
    }
    public Iterable<Field> getFields(long id) {
        Garden garden = getGarden(id);
        return garden.getFields();
    }

    // UPDATE
    public String addUser(User user, Garden garden) {
        garden.addOwner(user);
        repository.save( garden );
        return garden.getName() + " has now owner " + user.getUsername();
    }
    public Garden addNote(Garden garden, PostRequest toAdd) {
        Post newPost = new Post();
        toAdd.setCategory(PostCategory.NOTE);
        newPost = toAdd.convert();
        newPost.setAuthor(toAdd.getAuthor());
        garden.addPost(newPost);
        return repository.save(garden);
    }
    public Field addField(Field field, Garden garden) {
        field.setGarden(garden);
        garden.addField(field);
        repository.save(garden);
        return field;
    }
    public Garden updateGarden(long id, GardenRequest modified) {
        Garden garden = getGarden(id);
        try {
            if (!modified.getName().isBlank()) garden.setName(modified.getName());

            if (modified.getX() != 0 || modified.getY() != 0) {
                garden.setX(modified.getX());
                garden.setY(modified.getY());
                garden.setSize(garden.getX(), garden.getY());
            }

            if (!modified.getOwners().isEmpty()) garden.setOwners(modified.getOwners());

            if (!garden.getOwners().isEmpty()) garden.setTasks();

            return repository.save(garden);
        } catch (Exception e) {
            throw new BadRequestException("cannot update garden");
        }
    }
    public Garden updateSize(long id, int x, int y) {
        Garden garden = getGarden(id);
        try {
            if (x != 0 || y != 0) {
                garden.setX(x);
                garden.setY(y);
                garden.setSize(garden.getX(), garden.getY());
            }
            return repository.save(garden);
        } catch (Exception e) {
            throw new BadRequestException("cannot update garden size");
        }
    }
    public Iterable<Task> updateTasks(Garden toModify) {
        toModify.setTasks();
        return toModify.getTasks();
    }

    // DELETE
    public String delete(long id) {
        Garden g = getGarden(id);
        repository.deleteById(id);
        return g.getName() + " is deleted";
    }
    public String removeUser(User user, Garden garden) {
        garden.removeOwner(user);
        if (!garden.getOwners().isEmpty()) {
            repository.save(garden);
            return "removed " + user.getUsername() + " from garden " + garden.getName();
        } else {
            repository.delete(garden);
            return "removed " + garden.getName() + " because all owners are removed";
        }
    }
    public String deletePost(Post post, Garden garden) {
        garden.removePost(post);
        repository.save(garden);
        return "removed " + post.getTitle() + " from garden " + garden.getName();
    }
}
