package nl.mengelmoestuintjes.gardening.service;

import nl.mengelmoestuintjes.gardening.controller.exceptions.BadRequestException;
import nl.mengelmoestuintjes.gardening.controller.exceptions.InvalidPasswordException;
import nl.mengelmoestuintjes.gardening.controller.exceptions.NotAuthorizedException;
import nl.mengelmoestuintjes.gardening.controller.exceptions.UserNotFoundException;
import nl.mengelmoestuintjes.gardening.dto.request.UserRequest;
import nl.mengelmoestuintjes.gardening.model.users.Authority;
import nl.mengelmoestuintjes.gardening.model.users.Province;
import nl.mengelmoestuintjes.gardening.model.users.User;
import nl.mengelmoestuintjes.gardening.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository repository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean userExists(String username) {
        return repository.existsById(username);
    }

    // CREATE
    public String create(UserRequest request) {
        try {
            String encryptedPassword = passwordEncoder.encode( request.getPassword());
            User user = new User();
            user.setUsername(request.getUsername());
            user.setPassword(encryptedPassword);
            user.setEnabled(true);
            user.addAuthority("ROLE_USER");
            user.setEmail(request.getEmail());
            user.setLevel(request.getLevel());
            user.setXp(request.getXp());
            user.setLevelUpLimit(request.getLevelUpLimit());
            user.setName(request.getName());
            user.setBirthday(request.getBirthday());
            user.setProvince(request.getProvince());
            user.setMemberSince( LocalDate.now() );
            user.setLastActivity();
            User newUser = repository.save(user);
            return newUser.getUsername();
        }
        catch (Exception ex) {
            throw new BadRequestException("Cannot create user.");
        }
    }


    // READ
    private String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }

    public Iterable<User> getAll(
            String email, String level, Province province
    ) {
        if ( !email.isBlank() ) return repository.findAllByEmailContaining(email);
        if ( !level.isBlank() ) return repository.findByLevel( level );
        if ( province != null ) return repository.findAllByProvince(province);

        return repository.findAll();
    }

    public User getUser(String username) {
        Optional<User> toFind = repository.findById( username );
        boolean userFound = toFind.isPresent();
        if ( userFound ) {
            return toFind.get();
        } else {
            throw new UserNotFoundException(username);
        }
    }

    public List<Authority> getAuthorities(String username) {
        User toFind = getUser( username );
        return toFind.getAuthorities();
    }

    public String getXP(String username) {
        User toFind = getUser( username );
        return toFind.getLevel() + ":" +toFind.getXp() + " -> " + toFind.getLevelUpLimit();
    }

    public String addAuthority(String username, String authorityString) {
        User user = getUser( username );
        user.addAuthority(authorityString);
        repository.save(user);
        return user.getUsername() + " has now authorithy " + authorityString;
    }

    // UPDATE
    public String changeCredentials(User toChange, User changed){
        String out = toChange.getUsername() + " updated:";

        toChange.setPassword( passwordEncoder.encode(changed.getPassword()));

        if (toChange.isEnabled() != changed.isEnabled()) {
            toChange.setEnabled(changed.isEnabled());
            out += " enabled";
        }
        if (!toChange.getEmail().equalsIgnoreCase(changed.getEmail())) {
            toChange.setEmail(changed.getEmail());
            out += " email";
        }
        if (!toChange.getName().equalsIgnoreCase(changed.getName())) {
            toChange.setName(changed.getName());
            out += " name";
        }
        if (toChange.getProvince() != changed.getProvince() ) {
            toChange.setProvince(changed.getProvince());
            out += " province";
        }

        if (out.equalsIgnoreCase(toChange.getUsername() + " updated:")) {
            out = "nothing had been updated";
        }
        return out;
    }
    public String update(String username, User modified) {
        Optional<User> toFind = repository.findById(username);
        if (toFind.isEmpty()) { throw new UserNotFoundException(username);
        } else {
            User user = toFind.get();
            String changed = changeCredentials( user, modified );
            repository.save(user);
            return changed;
        }
    }
    public void setPassword(String username, String password) {
        if (username.equals(getCurrentUserName())) {
            if (isValidPassword(password)) {
                Optional<User> userOptional = repository.findById(username);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    user.setPassword(passwordEncoder.encode(password));
                    repository.save(user);
                } else { throw new UserNotFoundException(username); }
            } else { throw new InvalidPasswordException(); }
        } else { throw new NotAuthorizedException(); }
    }
    public LocalDate setBirthday(String username, LocalDate newBirthday) {
        User user = getUser( username );
        user.setBirthday( newBirthday );
        repository.save(user);
        return newBirthday;
    }
    public String setXP(String username, long toAdd) {
        User user = getUser( username );
        String xp = "" + toAdd;
        String out = user.setXp( xp );
        repository.save( user );
        return out;
    }
    public String setLastActivity(String username) {
        User user = getUser( username );
        user.setLastActivity();
        repository.save( user );
        return "last activity = " + user.getLastActivity();
    }

    // DELETE
    public void deleteUser(String username) {
        if (repository.existsById(username)) {
            repository.deleteById(username);
        }
        else {
            throw new UserNotFoundException(username);
        }
    }

    public void removeAuthority(String username, String authorityString) {
        Optional<User> userOptional = repository.findById(username);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(username);
        } else {
            User user = userOptional.get();
            user.removeAuthority(authorityString);
            repository.save(user);
        }
    }

    private boolean isValidPassword(String password) {
        final int MIN_LENGTH = 8;
        final int MIN_DIGITS = 1;
        final int MIN_LOWER = 1;
        final int MIN_UPPER = 1;
        final int MIN_SPECIAL = 1;
        final String SPECIAL_CHARS = "@#$%&*!()+=-_";

        long countDigit = password.chars().filter(ch -> ch >= '0' && ch <= '9').count();
        long countLower = password.chars().filter(ch -> ch >= 'a' && ch <= 'z').count();
        long countUpper = password.chars().filter(ch -> ch >= 'A' && ch <= 'Z').count();
        long countSpecial = password.chars().filter(ch -> SPECIAL_CHARS.indexOf(ch) >= 0).count();

        boolean validPassword = true;
        if (password.length() < MIN_LENGTH) validPassword = false;
        if (countLower < MIN_LOWER) validPassword = false;
        if (countUpper < MIN_UPPER) validPassword = false;
        if (countDigit < MIN_DIGITS) validPassword = false;
        if (countSpecial < MIN_SPECIAL) validPassword = false;

        return validPassword;
    }
    public Long convertStringToLong(String s) {
        if (s.equals("MAX")) {
            return (long) 0;
        }
        return Long.parseLong( s );
    }
    public boolean hasReachedLevelUpLimit(long current, String limit) {
        long levelUpLimit = convertStringToLong(limit);
        return current >= levelUpLimit;
    }
    public boolean userIsMax(String level, String limit) {
        boolean levelIsMax = level.equalsIgnoreCase("MAX");
        boolean isLevel99 = level.equalsIgnoreCase("99");
        boolean levelUpLimitMax = limit.equalsIgnoreCase("MAX");
        return levelIsMax || isLevel99 || levelUpLimitMax;
    }

}
