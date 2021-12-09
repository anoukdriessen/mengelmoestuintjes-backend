package nl.mengelmoestuintjes.gardening.service;

import nl.mengelmoestuintjes.gardening.exceptions.RecordNotFoundException;
import nl.mengelmoestuintjes.gardening.model.Plant;
import nl.mengelmoestuintjes.gardening.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlantServiceImpl implements PlantService {

    private PlantRepository plantRepository;

    @Autowired
    public PlantServiceImpl(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    @Override
    public List<Plant> getPlants() {
        return (List<Plant>) plantRepository.findAll();
    }

    @Override
    public Optional<Plant> getPlantById(long id) {
        if (!plantRepository.existsById(id)) throw new RecordNotFoundException();
        return plantRepository.findById(id);
    }

    @Override
    public int addPlant(Plant plant)  {
        return 0;
//        return ListOfPlants.addPlant(plant); TODO
    }

    @Override
    public void removePlantById(int id) {
        ArrayList<Plant> listOfPlants = new ArrayList<>();
        Plant toFind = listOfPlants.get(id);
//        Plant toFind = ListOfPlants.getPlant(id); TODO
        if (toFind == null) throw new RecordNotFoundException("Cannot find plant");
//        ListOfPlants.removePlant(id);
        listOfPlants.remove(id);
    }

    @Override
    public void updatePlantById(int id, Plant plant) {
        ArrayList<Plant> listOfPlants = new ArrayList<>();
        Plant toFind = listOfPlants.get(id);
//        Plant toFind = ListOfPlants.getPlant(id); TODO
        if (toFind == null) throw new RecordNotFoundException("Cannot find plant");
//        ListOfBooks.updateBook(id, book); TODO
    }
}
