package nl.mengelmoestuintjes.gardening.repository;

import nl.mengelmoestuintjes.gardening.model.User;
import nl.mengelmoestuintjes.gardening.model.garden.Garden;
import org.springframework.data.repository.CrudRepository;

public interface GardenRepository extends CrudRepository<Garden, Long> {

}
