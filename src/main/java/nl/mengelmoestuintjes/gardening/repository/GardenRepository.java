package nl.mengelmoestuintjes.gardening.repository;

import nl.mengelmoestuintjes.gardening.model.garden.Garden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GardenRepository extends JpaRepository<Garden, Long> {

}
