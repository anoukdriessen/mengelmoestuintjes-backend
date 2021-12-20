package nl.mengelmoestuintjes.gardening.service.users;

import nl.mengelmoestuintjes.gardening.model.users.User;
import nl.mengelmoestuintjes.gardening.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    // Create
    public User newUser(User toAdd) {
        return repository.save( toAdd );
    }

    // Read
    public Iterable<User> getAll() {
        return repository.findAll();
    }

}
