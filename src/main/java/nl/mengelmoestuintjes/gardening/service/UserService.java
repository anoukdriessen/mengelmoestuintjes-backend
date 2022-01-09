package nl.mengelmoestuintjes.gardening.service;

import nl.mengelmoestuintjes.gardening.controller.exceptions.BadRequestException;
import nl.mengelmoestuintjes.gardening.controller.exceptions.InvalidException;
import nl.mengelmoestuintjes.gardening.controller.exceptions.NotAuthorizedException;
import nl.mengelmoestuintjes.gardening.controller.exceptions.UserNotFoundException;
import nl.mengelmoestuintjes.gardening.dto.request.UserRequest;
import nl.mengelmoestuintjes.gardening.dto.response.UserResponse;
import nl.mengelmoestuintjes.gardening.model.posts.Post;
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
import java.util.ArrayList;
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
    private String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }

    // CREATE
    public String create(UserRequest request) {
        try {
            request.setMemberSince(LocalDate.now());

            UserResponse dto = new UserResponse();
            User user = dto.toUser(request);

            try {
                String encryptedPassword = passwordEncoder.encode(request.getPassword());
                user.setPassword(encryptedPassword);
            } catch (Exception e) {
                throw new InvalidException("password");
            }

            repository.save(user);
            return user.getUsername();
        }
        catch (Exception ex) {
            throw new BadRequestException("Cannot create user.");
        }
    }

    // READ
    public Iterable<User> getAll( String email, String level, Province province ) {
        try {
            boolean hasEmail = !email.isBlank();
            boolean hasLevel = !level.isBlank();
            boolean hasProvince = province != null;

            if (hasEmail) return repository.findAllByEmailContaining(email);
            if (hasLevel) return repository.findByLevel(level);
            if (hasProvince) return repository.findAllByProvince(province);

            return repository.findAll();
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }
    public User getUser(String username) {
        Optional<User> toFind = repository.findById( username );
        boolean userFound = toFind.isPresent();

        if ( userFound ) return toFind.get();
        else throw new UserNotFoundException(username);
    }
    public List<Authority> getAuthorities(String username) {
        User toFind = getUser( username );
        return toFind.getAuthorities();
    }
    public String getXP(String username) {
        User toFind = getUser( username );
        return toFind.getLevel() + ":" +toFind.getXp() + " -> " + toFind.getLevelUpLimit();
    }
    public List<Post> getPosts(String username, boolean published) {
        User toFind = getUser( username );
        ArrayList<Post> posted = new ArrayList<>();
        ArrayList<Post> concepts = new ArrayList<>();

        for (Post p : toFind.getPosts()) {
            if (p.isPublished()) {
                posted.add(p);
            } else {
                concepts.add(p);
            }
        }

        if (published) {
            return posted;
        } else {
            return concepts;
        }
    }

    // UPDATE
    public String addAuthority(String username, String authorityString) {
        User user = getUser( username );
        user.addAuthority(authorityString);
        repository.save(user);
        return user.getUsername() + " has now authority " + authorityString;
    }
    public ArrayList<String> change(User toChange, UserRequest changed){
        ArrayList<String> changedFields = new ArrayList<>();

        toChange.setPassword( passwordEncoder.encode(changed.getPassword()));
        boolean enabled = toChange.isEnabled() != changed.isEnabled();
        boolean email = !toChange.getEmail().equalsIgnoreCase(changed.getEmail());
        boolean name = !toChange.getName().equalsIgnoreCase(changed.getName());
        boolean province = toChange.getProvince() != changed.getProvince();

        if (enabled) {
            toChange.setEnabled(changed.isEnabled());
            changedFields.add("enabled");
        }
        if (email) {
            toChange.setEmail(changed.getEmail());
            changedFields.add("email");
        }
        if (name) {
            toChange.setName(changed.getName());
            changedFields.add("name");
        }
        if (province) {
            toChange.setProvince(changed.getProvince());
            changedFields.add("province");
        }

        if (changedFields.isEmpty()) {
            changedFields.add("NOTHING");
        }
        return changedFields;
    }
    public ArrayList<String> update(String username, UserRequest modified) {
        User user = getUser( username );
        ArrayList<String> changed = change( user, modified );
        repository.save(user);
        return changed;
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
            } else { throw new InvalidException("password"); }
        } else { throw new NotAuthorizedException(); }
    }
    public LocalDate setBirthday(String username, LocalDate newBirthday) {
        User user = getUser(username);
        try {
            user.setBirthday(newBirthday);
            repository.save(user);
            return newBirthday;
        } catch (Exception e) {
            throw new InvalidException("birthday");
        }
    }
    public String setXP(String username, long toAdd) {
        User user = getUser(username);
        try {
            String xp = "" + toAdd;
            String out = user.setXp(xp);
            repository.save(user);
            return out;
        } catch (Exception e) {
            throw new InvalidException("set xp to user " + username);
        }
    }
    public String setLastActivity(String username) {
        User user = getUser( username );
        try {
            user.setLastActivity();
            repository.save(user);
            return "last activity = " + user.getLastActivity();
        } catch (Exception e) {
            throw new InvalidException("last activity");
        }
    }

    // DELETE
    public void deleteUser(String username) {
        if (userExists( username )) repository.deleteById(username);
        else throw new UserNotFoundException(username);
    }
    public void removeAuthority(String username, String authorityString) {
        User user = getUser( username );
        try {
            user.removeAuthority(authorityString);
            repository.save(user);
        } catch (Exception e) {
            throw new InvalidException("cannot remove authority " + authorityString + " from user " + username);
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
}
