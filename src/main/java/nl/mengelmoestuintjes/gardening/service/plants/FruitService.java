//package nl.mengelmoestuintjes.gardening.service.plants;
//
//import nl.mengelmoestuintjes.gardening.exceptions.RecordNotFoundException;
//import nl.mengelmoestuintjes.gardening.model.plants.Flower;
//import nl.mengelmoestuintjes.gardening.model.plants.Fruit;
//import nl.mengelmoestuintjes.gardening.model.plants.Vegetable;
//import nl.mengelmoestuintjes.gardening.repository.plants.FlowerRepository;
//import nl.mengelmoestuintjes.gardening.repository.plants.FruitRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class FruitService {
//    private static final String NOT_FOUND = "Fruit not found";
//    private final FruitRepository plantRepository;
//
//    @Autowired
//    public FruitService(FruitRepository plantRepository) {
//        this.plantRepository = plantRepository;
//    }
//
//    // create
//    public Fruit newPlant(Fruit toAdd) {
//        return plantRepository.save( toAdd );
//    }
//
//    // read
//    public Iterable<Fruit> getAllPlants( String name ){
//        if ( !name.isBlank() ) {
//            return plantRepository.findAllByNameContaining(name);
////        } else if ( !location.isBlank() ) {
////            return plantRepository.findAllByLocation(location);
////        } else if ( !ground.isBlank() ){
////            return plantRepository.findAllByGround(ground);
////        } else if ( frostResistance ) {
////            return plantRepository.findByFrostResistanceTrue();
//        } else {
////            return plantRepository.findAllByOrderByNameAsc();
//            return plantRepository.findAll();
//        }
//    }
//    public Fruit getPlantById( int id ) {
//        Optional<Fruit> toFind = plantRepository.findById(id);
//
//        if (toFind.isPresent()) { // check if plant exists
//            return toFind.get();
//        } else {  // plant does not exists
//            throw new RecordNotFoundException(NOT_FOUND);
//        }
//    }
//
//    // update
//    public void updatePlant( int id, Fruit modified ) {
//        Fruit toModify = plantRepository.findById(id).orElse(null);
//
//        if ( toModify != null ) {
//            // TODO check not empty
//            plantRepository.save(toModify);
//        } else {
//            throw new RecordNotFoundException(NOT_FOUND);
//        }
//    }
//
//    // delete
//    public void deletePlantById(int id) {
//        Optional<Fruit> toFind = plantRepository.findById(id);
//
//        if (toFind.isPresent()) { // check if plant exists
//            plantRepository.deleteById(id);
//        } else { // plant does not exists
//            throw new RecordNotFoundException(NOT_FOUND);
//        }
//    }
//
//}
