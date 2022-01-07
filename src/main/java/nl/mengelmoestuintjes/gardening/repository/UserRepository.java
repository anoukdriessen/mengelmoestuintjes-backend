package nl.mengelmoestuintjes.gardening.repository;

import nl.mengelmoestuintjes.gardening.model.users.Province;
import nl.mengelmoestuintjes.gardening.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    Iterable<User> findAllByEmailContaining(String email);
    // TODO add sorting/paging
    Iterable<User> findByLevel(String level);
    Iterable<User> findAllByProvince(Province province);
}
