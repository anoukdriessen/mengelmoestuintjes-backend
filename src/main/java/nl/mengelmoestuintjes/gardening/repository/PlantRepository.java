//package nl.mengelmoestuintjes.gardening.repository;
//
//import nl.mengelmoestuintjes.gardening.model.garden.plants.Location;
//import nl.mengelmoestuintjes.gardening.model.garden.plants.Plant;
//import nl.mengelmoestuintjes.gardening.model.garden.plants.Category;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface PlantRepository extends JpaRepository<Plant, Long> {
//
//    Iterable<Plant> findPlantByNameContaining(String name);
//    Iterable<Plant> findPlantByCategory(Category category);
//    Iterable<Plant> findPlantByLocation(Location location);
//
//    Iterable<Plant> findPlantsByOutdoors(String outdoors);
//    Iterable<Plant> findPlantsByIndoors(String indoors);
//    Iterable<Plant> findPlantsByHarvest(String harvest);
//}
