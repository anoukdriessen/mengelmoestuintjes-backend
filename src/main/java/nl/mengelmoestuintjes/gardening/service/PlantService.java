//package nl.mengelmoestuintjes.gardening.service;
//
//import nl.mengelmoestuintjes.gardening.controller.exceptions.RecordNotFoundException;
//import nl.mengelmoestuintjes.gardening.model.plants.*;
//import nl.mengelmoestuintjes.gardening.repository.PlantRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Objects;
//import java.util.Optional;
//
//@Service
//public class PlantService {
//    private static final String NOT_FOUND = "plant not found";
//    private final PlantRepository repository;
//    @Autowired
//    public PlantService(PlantRepository repository) {
//        this.repository = repository;
//    }
//
//    public Plant newPlant(Plant toAdd) {
//        return repository.save( toAdd );
//    }
//    public Plant newFlower(Plant toAdd){
//        toAdd.setCategory(Category.FLOWERS);
//        return repository.save( toAdd );
//    }
//    public Plant newFruit(Plant toAdd){
//        toAdd.setCategory(Category.FRUITS);
//        return repository.save( toAdd );
//    }
//    public Plant newHerb(Plant toAdd){
//        toAdd.setCategory(Category.HERBS);
//        return repository.save( toAdd );
//    }
//    public Plant newVegetable(Plant toAdd){
//        toAdd.setCategory(Category.VEGETABLES);
//        return repository.save( toAdd );
//    }
//
//    public Iterable<Plant> getAll(
//            String name, Category category, Location location, String outdoor, String indoor, String harvest) {
//        if ( !name.isBlank() ) return repository.findPlantByNameContaining(name);
//        if ( !Objects.isNull(category) ) return repository.findPlantByCategory(category);
//        if ( !Objects.isNull(location) ) return repository.findPlantByLocation(location);
//        if ( !outdoor.isBlank() ) return repository.findPlantsByOutdoors(outdoor);
//        if ( !indoor.isBlank() ) return repository.findPlantsByIndoors(indoor);
//        if ( !harvest.isBlank() ) return repository.findPlantsByHarvest(harvest);
//
//        return repository.findAll();
//    }
//
//    public Plant getById( long id ) {
//        Optional<Plant> toFind = repository.findById(id);
//        if (toFind.isPresent()) {   // check if quote exists
//            return toFind.get();
//        } else {                    // post does not exists
//            throw new RecordNotFoundException(NOT_FOUND);
//        }
//    }
//
//    public void update( long id, Plant modified ){
//        Plant toModify = repository.findById( id ).orElse( null );
//        if (toModify != null) {
//            toModify.setCategory( modified.getCategory() );
//            toModify.setName( modified.getName() );
//            toModify.setDescription( modified.getDescription() );
//
//            repository.save(toModify);
//        } else {
//            throw new RecordNotFoundException(NOT_FOUND);
//        }
//    }
//
//    public Plant delete( long id ) {
//        Optional<Plant> toFind = repository.findById( id );
//        if (toFind.isPresent()) {  // check if plant exists
//            Plant toDelete = toFind.get();
//            repository.delete( toDelete );
//            return toDelete;
//        } else {  // plant does not exists
//            throw new RecordNotFoundException(NOT_FOUND);
//        }
//    }
//}
