package nl.mengelmoestuintjes.gardening.service;

import nl.mengelmoestuintjes.gardening.exceptions.RecordNotFoundException;
import nl.mengelmoestuintjes.gardening.model.users.User;
import nl.mengelmoestuintjes.gardening.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    private static final String NOT_FOUND = "user not found";
    private final UserRepository repository;
    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User newUser(User toAdd) {
        toAdd.setMemberSince( LocalDateTime.now() );
        return repository.save( toAdd );
    }

    public Iterable<User> getAll() {
        return repository.findAll();
    }

    public User getById( String username ) {
        Optional<User> toFind = repository.findById( username );
        if (toFind.isPresent()) {   // check if quote exists
            return toFind.get();
        } else {                    // post does not exists
            throw new RecordNotFoundException(NOT_FOUND);
        }
    }

    public void update( String username, User modified ){
        User toModify = repository.findById( username ).orElse( null );
        if (toModify != null) {
            boolean passwordEmpty = modified.getPassword().isEmpty();
            boolean lvlNotNull = modified.getLvl() != 0;
            boolean xpNotNull = modified.getXp() != 0;
            boolean nameEmpty = modified.getName().isEmpty();
            boolean birthdayEmpty = modified.getBirthday() == null;
            boolean provinceEmpty = modified.getProvince() == null;
            boolean roleEmpty = modified.getRole() == null;
            boolean levelLimitNotEmpty = modified.getLevelUpLimit() != 0;

            if ( !passwordEmpty ) toModify.setPassword( modified.getPassword() );
            if ( lvlNotNull ) toModify.setLvl( modified.getLvl() );
            if ( xpNotNull ) toModify.setXp( modified.getXp() );
            if ( !nameEmpty ) toModify.setName( modified.getName() );
            if ( !birthdayEmpty ) toModify.setBirthday( modified.getBirthday() );
            if ( !provinceEmpty ) toModify.setProvince( modified.getProvince() );
            if ( !roleEmpty ) toModify.setRole( modified.getRole() );
            if ( levelLimitNotEmpty ) toModify.setLevelUpLimit( modified.getLevelUpLimit() );
            toModify.setLastActivity( LocalDateTime.now() );

            repository.save(toModify);
        } else {
            throw new RecordNotFoundException(NOT_FOUND);
        }
    }

    public User delete( String id ) {
        Optional<User> toFind = repository.findById( id );
        if (toFind.isPresent()) {  // check if user exists
            User toDelete = toFind.get();
            repository.delete( toDelete );
            return toDelete;
        } else {  // user does not exists
            throw new RecordNotFoundException(NOT_FOUND);
        }
    }
}
