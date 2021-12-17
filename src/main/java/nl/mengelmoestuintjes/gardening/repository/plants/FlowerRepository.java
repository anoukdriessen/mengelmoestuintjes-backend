package nl.mengelmoestuintjes.gardening.repository.plants;

import nl.mengelmoestuintjes.gardening.model.plants.Flower;
import nl.mengelmoestuintjes.gardening.model.plants.Vegetable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlowerRepository extends JpaRepository<Flower, Integer> {

    Iterable<Flower> findAllByNameContaining(String name);
//    Iterable<Flower> findAllByLocation(String location);
//    Iterable<Flower> findAllByGround(String ground);
//    Iterable<Flower> findByFrostResistanceTrue();
    Iterable<Flower> findAllByOrderByNameAsc();
}
