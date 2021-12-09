package nl.mengelmoestuintjes.gardening.repository;

import nl.mengelmoestuintjes.gardening.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface PlantRepository extends CrudRepository<Plant, Long> , JpaRepository<Plant, Long> {
    Collection<Plant> findByCategoryLike(String categoryName);
}
