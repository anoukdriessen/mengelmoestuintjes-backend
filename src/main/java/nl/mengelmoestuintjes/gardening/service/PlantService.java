package nl.mengelmoestuintjes.gardening.service;

import nl.mengelmoestuintjes.gardening.exceptions.RecordNotFoundException;
import nl.mengelmoestuintjes.gardening.model.Plant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlantService {
    public List<Plant> getPlants() {
        return new ArrayList<>();
//        return ListOfPlants.getPlants(); TODO
    }

    public Plant getPlantById(int id) {
        ArrayList<Plant> listOfPlants = new ArrayList<>();
//        Plant toFind = ListOfPlants.getPlant(id); TODO
        Plant toFind = listOfPlants.get(id);
        if (toFind == null) throw new RecordNotFoundException("Cannot find plant");
        return toFind;
    }

    public int addPlant(Plant plant) {
        return 0;
//        return ListOfPlants.addPlant(plant); TODO
    }

    public void removePlantById(int id) {
        ArrayList<Plant> listOfPlants = new ArrayList<>();
        Plant toFind = listOfPlants.get(id);
//        Plant toFind = ListOfPlants.getPlant(id); TODO
        if (toFind == null) throw new RecordNotFoundException("Cannot find plant");
//        ListOfPlants.removePlant(id);
        listOfPlants.remove(id);
    }

    public void updatePlantById(int id, Plant plant) {
        ArrayList<Plant> listOfPlants = new ArrayList<>();
        Plant toFind = listOfPlants.get(id);
//        Plant toFind = ListOfPlants.getPlant(id); TODO
        if (toFind == null) throw new RecordNotFoundException("Cannot find plant");
//        ListOfBooks.updateBook(id, book); TODO
    }
}
