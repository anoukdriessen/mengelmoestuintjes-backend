package nl.mengelmoestuintjes.gardening.service;

import nl.mengelmoestuintjes.gardening.controller.exceptions.GardenNotFoundException;
import nl.mengelmoestuintjes.gardening.dto.request.GardenRequest;
import nl.mengelmoestuintjes.gardening.model.User;
import nl.mengelmoestuintjes.gardening.model.garden.Garden;
import nl.mengelmoestuintjes.gardening.repository.GardenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GardenService {

    private final GardenRepository repository;
    @Autowired
    public GardenService(GardenRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public String create(GardenRequest toAdd) {
        Garden newGarden = new Garden();

        newGarden.setId(toAdd.getId());
        newGarden.setName(toAdd.getName());
        newGarden.setOwners(toAdd.getOwners());
        repository.save(newGarden);
        return newGarden.getName() + " created";
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
    public Iterable<String> getUsers(long id) {
        Garden garden = getGarden(id);
        return garden.getOwners();
    }

    // UPDATE
    public String addUser(User user, Garden garden) {
        garden.addOwner(user);
        repository.save( garden );
        return garden.getName() + " has now owner " + user.getUsername();
    }
    public String updateGarden(long id, GardenRequest modified) {
        Garden garden = getGarden(id);

        garden.setName(modified.getName());
        garden.setOwners(modified.getOwners());

        repository.save(garden);
        return garden.getName() + " has been updated";
    }
//    public String updateX(long id, byte x) {
//        Garden garden = getGarden(id);
//        garden.setX(x);
//        repository.save(garden);
//        return "new x=" + garden.getX() + " size=" + garden.getSize();
//    }
//    public String updateY(long id, byte y) {
//        Garden garden = getGarden(id);
//        garden.setY(y);
//        repository.save(garden);
//        return "new y=" + garden.getY() + " size=" + garden.getSize();
//    }

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
