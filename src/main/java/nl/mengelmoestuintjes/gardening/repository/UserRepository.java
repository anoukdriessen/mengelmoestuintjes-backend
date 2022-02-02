package nl.mengelmoestuintjes.gardening.repository;

import nl.mengelmoestuintjes.gardening.model.Province;
import nl.mengelmoestuintjes.gardening.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
//    Iterable<User> findAllByEmailContaining(String email);
//    Iterable<User> findByLevel(String level);
    Iterable<User> findAllByProvince(Province province);
    boolean existsByEmail(String email);
}
