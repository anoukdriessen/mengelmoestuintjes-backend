package nl.mengelmoestuintjes.gardening.service;

import nl.mengelmoestuintjes.gardening.exceptions.RecordNotFoundException;
import nl.mengelmoestuintjes.gardening.model.Plant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public interface PlantService {
    public List<Plant> getPlants();

    public Optional<Plant> getPlantById(long id);

    public int addPlant(Plant plant);

    public void removePlantById(int id);

    public void updatePlantById(int id, Plant plant);
}
