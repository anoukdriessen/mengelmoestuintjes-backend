package nl.mengelmoestuintjes.gardening.service;

import nl.mengelmoestuintjes.gardening.controller.exceptions.BadRequestException;
import nl.mengelmoestuintjes.gardening.controller.exceptions.GardenNotFoundException;
import nl.mengelmoestuintjes.gardening.dto.request.GardenRequest;
import nl.mengelmoestuintjes.gardening.model.Task;
import nl.mengelmoestuintjes.gardening.model.User;
import nl.mengelmoestuintjes.gardening.model.garden.Field;
import nl.mengelmoestuintjes.gardening.model.garden.Garden;
import nl.mengelmoestuintjes.gardening.repository.GardenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

@Service
public class GardenService {

    private final GardenRepository repository;
    @Autowired
    public GardenService(GardenRepository repository) {
        this.repository = repository;
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

            if (!newGarden.getOwners().isEmpty()) newGarden.setTasks();

            return repository.save(newGarden);
        } catch (Exception e) {
            throw new BadRequestException("cannot create garden");
        }

    }

    // READ
    public Iterable<Garden> getAll() {
        return repository.findAll();
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
    public HashMap<Long, ArrayList<String>> findGardensByOwnersEquals(User username){
        HashMap<Long, ArrayList<String>> idAndUsers = new HashMap<>();
        Iterable<Garden> all = getAll();
        for (Garden g : all) {
            idAndUsers.put(g.getId(), g.getOwners());
        }
        return idAndUsers;
    }
    public Iterable<String> getUsers(long id) {
        Garden garden = getGarden(id);
        return garden.getOwners();
    }
    public Iterable<Task> getTasks(long id) {
        Garden garden = getGarden(id);
        return garden.getTasks();
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
}
