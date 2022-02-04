package nl.mengelmoestuintjes.gardening.service;

import nl.mengelmoestuintjes.gardening.controller.exceptions.BadRequestException;
import nl.mengelmoestuintjes.gardening.controller.exceptions.RecordNotFoundException;
import nl.mengelmoestuintjes.gardening.model.garden.plants.Category;
import nl.mengelmoestuintjes.gardening.model.garden.plants.Plant;
import nl.mengelmoestuintjes.gardening.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class PlantService {
    private final PlantRepository repository;
    @Autowired
    public PlantService(PlantRepository repository) {
        this.repository = repository;
    }

    public Plant newPlant(Plant toAdd) {
        if (repository.existsByName(toAdd.getName())) {
            throw new BadRequestException("Name already exists");
        }
        return repository.save( toAdd );
    }
    public Plant newFlower(Plant toAdd){
        toAdd.setCategory(Category.FLOWERS);
        return repository.save( toAdd );
    }
    public Plant newFruit(Plant toAdd){
        toAdd.setCategory(Category.FRUITS);
        return repository.save( toAdd );
    }
    public Plant newHerb(Plant toAdd){
        toAdd.setCategory(Category.HERBS);
        return repository.save( toAdd );
    }
    public Plant newVegetable(Plant toAdd){
        toAdd.setCategory(Category.VEGETABLES);
        return repository.save( toAdd );
    }

//    public Iterable<Plant> getAll(String name, Category category, Location location, String outdoor, String indoor, String harvest) {
    public Iterable<Plant> getAll(Category category) {
//        if ( !name.isBlank() ) return repository.findPlantByNameContaining(name);
//        if ( !Objects.isNull(location) ) return repository.findPlantByLocation(location);
//        if ( !outdoor.isBlank() ) return repository.findPlantsByOutdoors(outdoor);
//        if ( !indoor.isBlank() ) return repository.findPlantsByIndoors(indoor);
//        if ( !harvest.isBlank() ) return repository.findPlantsByHarvest(harvest);
        if ( !Objects.isNull(category) ) return repository.findAllByCategory(category);

        return repository.findAll();
    }

    public Plant getById( long id ) {
        Optional<Plant> toFind = repository.findById(id);
        if (toFind.isPresent()) {   // check if plant exists
            return toFind.get();
        } else {                    // plant does not exists
            throw new RecordNotFoundException("post not found");
        }
    }
    public Plant findPlantByName( String name ) {
        Optional<Plant> toFind = repository.findPlantByName( name );
        if (toFind.isPresent()) {   // check if plant exists
            return toFind.get();
        } else {                    // plant does not exists
            return null;
        }
    }

    public Plant update ( long id, Plant modified ) {
        Plant found = getById(id);
        if (!found.getName().equals(modified.getName())) {
            found.setName(modified.getName());
        }
        if (!found.getDescription().equals(modified.getDescription())) {
            found.setDescription(modified.getDescription());
        }
        if (!found.getCategory().equals(modified.getCategory())) {
            found.setCategory(modified.getCategory());
        }
        repository.save(found);
        return found;
    }

    public Plant delete( long id ) {
        Optional<Plant> toFind = repository.findById( id );
        if (toFind.isPresent()) {  // check if plant exists
            Plant toDelete = toFind.get();
            repository.delete( toDelete );
            return toDelete;
        } else {  // plant does not exists
            throw new RecordNotFoundException("plant not found");
        }
    }
}
