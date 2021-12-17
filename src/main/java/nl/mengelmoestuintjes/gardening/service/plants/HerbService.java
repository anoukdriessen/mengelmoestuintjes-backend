package nl.mengelmoestuintjes.gardening.service.plants;

import nl.mengelmoestuintjes.gardening.exceptions.RecordNotFoundException;
import nl.mengelmoestuintjes.gardening.model.plants.Flower;
import nl.mengelmoestuintjes.gardening.model.plants.Herb;
import nl.mengelmoestuintjes.gardening.model.plants.Vegetable;
import nl.mengelmoestuintjes.gardening.repository.plants.FruitRepository;
import nl.mengelmoestuintjes.gardening.repository.plants.HerbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HerbService {
        private static final String NOT_FOUND = "Herb not found";
        private final HerbRepository plantRepository;

        @Autowired
        public HerbService(HerbRepository plantRepository) {
            this.plantRepository = plantRepository;
        }

        // create
        public Herb newPlant(Herb toAdd) {
                return plantRepository.save( toAdd );
        }

        // read
        public Iterable<Herb> getAllPlants( String name ){
                if ( !name.isBlank() ) {
                        return plantRepository.findAllByNameContaining(name);
//        } else if ( !location.isBlank() ) {
//            return plantRepository.findAllByLocation(location);
//        } else if ( !ground.isBlank() ){
//            return plantRepository.findAllByGround(ground);
//        } else if ( frostResistance ) {
//            return plantRepository.findByFrostResistanceTrue();
                } else {
//                        return plantRepository.findAllByOrderByNameAsc();
                        return plantRepository.findAll();
                }
        }
        public Herb getPlantById( int id ) {
                Optional<Herb> toFind = plantRepository.findById(id);

                if (toFind.isPresent()) { // check if plant exists
                        return toFind.get();
                } else {  // plant does not exists
                        throw new RecordNotFoundException(NOT_FOUND);
                }
        }

        // update
        public void updatePlant( int id, Herb modified ) {
                Herb toModify = plantRepository.findById(id).orElse(null);

                if ( toModify != null ) {
                        // TODO check not empty
                        plantRepository.save(toModify);
                } else {
                        throw new RecordNotFoundException(NOT_FOUND);
                }
        }

        // delete
        public void deletePlantById(int id) {
                Optional<Herb> toFind = plantRepository.findById(id);

                if (toFind.isPresent()) { // check if plant exists
                        plantRepository.deleteById(id);
                } else { // plant does not exists
                        throw new RecordNotFoundException(NOT_FOUND);
                }
        }

}
