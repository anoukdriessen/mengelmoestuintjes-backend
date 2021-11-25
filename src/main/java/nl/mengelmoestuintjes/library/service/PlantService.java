package nl.mengelmoestuintjes.library.service;

import nl.mengelmoestuintjes.library.exceptions.RecordNotFoundException;
import nl.mengelmoestuintjes.library.model.Plant;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class to handle the Service for Plants
 * @author anoukdriessen
 */
@Service
public class PlantService {

    // method to get all plants
    public List<Plant> getPlants() {
        return ListOfPlants.getPlants();
    }

    // method to get plant by id
    public Plant getPlantById(int id) {
        Plant plantFound = ListOfPlants.getPlant(id);

        // check if plant can be found
        checkIfFound(plantFound);

        return plantFound;
    }

    // method to add plant to list
    public int addPlant(Plant plantToAdd) {
        return ListOfPlants.addPlant(plantToAdd);
    }

    // method to remove plant from list by id
    public void removePlantById(int id) {
        Plant plantFound = ListOfPlants.getPlant(id);

        // check if plant can be found
        checkIfFound(plantFound);

        ListOfPlants.removePlant(id);
    }

    // method to update plant (already in list)
    public void updatePlant(int id, Plant plantToUpdate) {
        Plant plantFound = ListOfPlants.getPlant(id);

        // check if plant can be found
        checkIfFound(plantFound);

        ListOfPlants.updatePlant(id, plantToUpdate);
    }

    // method to check if plant can be found
    public void checkIfFound(Plant plantFound) {
        if (plantFound == null) throw new RecordNotFoundException("Cannot find plant");
    }
}
