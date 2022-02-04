package nl.mengelmoestuintjes.gardening.service;

import nl.mengelmoestuintjes.gardening.controller.exceptions.BadRequestException;
import nl.mengelmoestuintjes.gardening.controller.exceptions.GardenNotFoundException;
import nl.mengelmoestuintjes.gardening.controller.exceptions.RecordNotFoundException;
import nl.mengelmoestuintjes.gardening.dto.request.GardenRequest;
import nl.mengelmoestuintjes.gardening.dto.request.PostRequest;
import nl.mengelmoestuintjes.gardening.dto.response.GardenResponse;
import nl.mengelmoestuintjes.gardening.dto.response.UserResponse;
import nl.mengelmoestuintjes.gardening.model.Post;
import nl.mengelmoestuintjes.gardening.model.PostCategory;
import nl.mengelmoestuintjes.gardening.model.User;
import nl.mengelmoestuintjes.gardening.model.garden.Field;
import nl.mengelmoestuintjes.gardening.model.garden.Garden;
import nl.mengelmoestuintjes.gardening.model.garden.plants.Plant;
import nl.mengelmoestuintjes.gardening.repository.GardenRepository;
import nl.mengelmoestuintjes.gardening.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GardenService {

    private final GardenRepository repository;
    private final PlantRepository plantRepository;

    @Autowired
    public GardenService(GardenRepository repository, PlantRepository plantRepository) {
        this.repository = repository;
        this.plantRepository = plantRepository;
    }

    // CREATE
    public Garden create(GardenRequest toAdd) {
        GardenRequest request = new GardenRequest();

        request.setId(toAdd.getId());
        request.setName(toAdd.getName());
        request.setSize(toAdd.getX(), toAdd.getY());

        request.setFields(toAdd.getFields());
        request.setOwners(toAdd.getOwners());
        request.setPosts(toAdd.getPosts());

        return repository.save(request.toGarden());
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
        Garden found = getGarden(id);
        return found.getOwners();
    }

    public Iterable<Post> getPosts(long id) {
        Garden found = getGarden(id);
        return found.getPosts();
    }

    public Iterable<Plant> getPlantsInGarden(long id) {
        Iterable<Field> fields = getFields(id);
        List<Plant> all = new ArrayList<>();
        fields.forEach(field -> {
            List<Plant> plants = field.getOccupiedBy();
            plants.forEach(plant -> {
                if (!all.contains(plant)) {
                    all.add(plant);
                }
            });
        });
        return all;
    }

    public Iterable<Field> getFields(long id) {
        Garden found = getGarden(id);
        return found.getFields();
    }

    public Field getFieldById(long id, int fieldToFind) {
        Garden found = getGarden(id);
        for (Field field : found.getFields()) {
            Field thisField = field;
            if (thisField.getId() == fieldToFind) {
                return found.getFields().get(thisField.getId() -1 );
            }
        }
        return null;
    }

    public Field getFieldByName(long id, String nameToFind) {
        Garden found = getGarden(id);
        for (Field field : found.getFields()) {
            Field thisField = field;
            if (thisField.getName().equals(nameToFind)) {
                return found.getFields().get(thisField.getId() -1);
            }
        }
        return null;
    }

    public List<Plant> getPlantsByField(long id, String fieldName) {
        Field toFind = getFieldByName(id, fieldName);
        return toFind.getOccupiedBy();
    }

    public Plant getPlantOnFieldById(long id, String fieldName, int plantid) {
        Field toFind = getFieldByName(id, fieldName);
        try {
            return toFind.getOccupiedBy().get(plantid - 1);
        } catch (Exception e) {
            throw new BadRequestException("Not valid index for plant");
        }
    }

    // UPDATE
    public ArrayList<UserResponse> addUser(User user, Garden garden) {
        garden.addOwner(user);
        repository.save( garden );
        return garden.getOwners();
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

    public Field addPlantToField(Garden garden, int fieldId, Long plantId) {
        Field field = getFieldById(garden.getId(), fieldId);
        Optional<Plant> toFind = plantRepository.findById(plantId);
        if (toFind.isEmpty()) {
            throw new RecordNotFoundException("Plant not found");
        }
        if (field != null) {
            Plant found = toFind.get();
            field.addPlant(found);
            repository.save(garden);
            return field;
        } else {
            throw new RecordNotFoundException("Field not found");
        }
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

    // DELETE
    public String delete(long id) {
        Garden found = getGarden(id);
        repository.deleteById(id);
        return found.getName() + " is deleted";
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

    public String deleteField(Garden garden, String name) {
        Field toDelete = getFieldByName(garden.getId(), name);
        if(toDelete == null) { throw new RecordNotFoundException("Field does not exist"); }
        String deleted = toDelete.getName();
        garden.removeField(toDelete);
        repository.save(garden);
        return deleted;
    }

    public Field removePlantFromField(Garden garden, int fieldId, Long plantId) {
        Field field = getFieldById(garden.getId(), fieldId);
        Optional<Plant> toFind = plantRepository.findById(plantId);
        if (toFind.isEmpty()) {
            throw new RecordNotFoundException("Plant not found");
        }
        if (field != null) {
            Plant found = toFind.get();
            field.removePlant(found);
            repository.save(garden);
            return field;
        } else {
            throw new RecordNotFoundException("Field not found");
        }
    }

}
