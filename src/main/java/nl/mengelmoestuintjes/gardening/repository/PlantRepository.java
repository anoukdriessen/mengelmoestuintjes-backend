package nl.mengelmoestuintjes.gardening.repository;

import nl.mengelmoestuintjes.gardening.model.garden.plants.Category;
import nl.mengelmoestuintjes.gardening.model.garden.plants.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlantRepository extends JpaRepository<Plant, Long> {
    boolean existsByName(String name);
    Iterable<Plant> findAllByCategory(Category category);
    Optional<Plant> findPlantByName(String name);
//    Iterable<Plant> findPlantByCategory(Category category);
//    Iterable<Plant> findPlantByLocation(Location location);
//    Iterable<Plant> findPlantsByOutdoors(String outdoors);
//    Iterable<Plant> findPlantsByIndoors(String indoors);
//    Iterable<Plant> findPlantsByHarvest(String harvest);
}
