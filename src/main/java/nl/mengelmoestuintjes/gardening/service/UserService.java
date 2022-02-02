package nl.mengelmoestuintjes.gardening.service;

import nl.mengelmoestuintjes.gardening.controller.exceptions.BadRequestException;
import nl.mengelmoestuintjes.gardening.controller.exceptions.InvalidException;
import nl.mengelmoestuintjes.gardening.controller.exceptions.NotAuthorizedException;
import nl.mengelmoestuintjes.gardening.controller.exceptions.UserNotFoundException;
import nl.mengelmoestuintjes.gardening.dto.request.PostRequest;
import nl.mengelmoestuintjes.gardening.dto.request.TaskRequest;
import nl.mengelmoestuintjes.gardening.dto.request.UserRequest;
import nl.mengelmoestuintjes.gardening.dto.response.UserResponse;
import nl.mengelmoestuintjes.gardening.model.*;
import nl.mengelmoestuintjes.gardening.model.garden.Garden;
import nl.mengelmoestuintjes.gardening.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserService {

    private UserRepository repository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean userExistsByUsername(String username) {
        return repository.existsById(username);
    }
    public boolean userExistsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    private String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }

    // CREATE
    public User create(User toAdd) {
        User user = new User();
        if (toAdd.getUsername() == null || toAdd.getUsername().isEmpty() || toAdd.getUsername().isBlank()) {
            throw new BadRequestException("Username is missing or invalid");
        } else if (toAdd.getPassword() == null || toAdd.getPassword().isEmpty() || toAdd.getPassword().isBlank()){
            throw new BadRequestException("Password is missing or invalid");
        } else if (toAdd.getEmail() == null || toAdd.getEmail().isEmpty() || toAdd.getEmail().isBlank()) {
            throw new BadRequestException("Email is missing or invalid");
        } else {
            try {
                String encryptedPassword = passwordEncoder.encode(toAdd.getPassword());
                user.setPassword(encryptedPassword);
            } catch (Exception e) {
                throw new BadRequestException("password is invalid");
            }
            String chosenUsername = toAdd.getUsername();
            String chosenEmail = toAdd.getEmail();

            if (userExistsByUsername(chosenUsername) || userExistsByEmail(chosenEmail)) {
                throw new BadRequestException("username or email already exists");
            }

            user.setUsername(toAdd.getUsername());
            try {
                user.setEmail(toAdd.getEmail());
            } catch (Exception e) {
                throw new BadRequestException("email is invalid");
            }

            user.setDefaultValues();
            user.addAuthority("ROLE_USER");

            repository.save(user);
            return user;
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
    public HashMap<String, byte[]> getAllProfiles() {
        HashMap<String, byte[]> all = new HashMap<>();
        Iterable<User> users = getAll("", "", null);
        users.forEach(user -> { all.put(user.getUsername(), user.getProfileImg());});
        return all;
    }
    public User getUser(String username) {
        Optional<User> toFind = repository.findById( username );
        boolean userFound = toFind.isPresent();

        if ( userFound ) {
            User found = toFind.get();
            found.setLastActivity(LocalDate.now());
            return found;
        }
        else throw new UserNotFoundException(username);
    }
    public UserResponse getUsersProfile(String username) {
        Optional<User> toFind = repository.findById( username );
        boolean userFound = toFind.isPresent();

        if ( userFound ) {
            User found = toFind.get();
            return found.getUserProfile();
        } else {
            throw  new UserNotFoundException(username);
        }
    }
    public List<String> getAuthorities(String username) {
        User toFind = getUser( username );
        return toFind.getAuthorities();
    }
    public List<String> getProvinces() {
        List<String> all = new ArrayList<>();
        for (Province p : Province.values()) {
            all.add(p.name());
        }
        return all;
    }
    public String[] getXP(String username) {
        User toFind = getUser( username );
        String[] stats = new String[3];
        stats[0] = toFind.getLevel();
        stats[1] = toFind.getXp();
        stats[2] = toFind.getLevelUpLimit();
        return stats;
    }
    public Iterable<String> getWithBirthdayToday() {
        Iterable<User> all = getAll("", "", null);
        ArrayList<String> isBirthday = new ArrayList<>();

        for (User u : all) {
            if (u.birthdayIsToday()) isBirthday.add(u.getUsername());
        }
        return isBirthday;
    }
    public List<Post> getPosts(String username, String category, boolean published) {
        User toFind = getUser( username );

        ArrayList<Post> privateNotes = new ArrayList<>();
        ArrayList<Post> privatePosts = new ArrayList<>();
        ArrayList<Post> publicPosts = new ArrayList<>();

        for (Post p : toFind.getPosts()) {
            if (p.getCategory() == PostCategory.NOTE) {
                // post is a private note
                privateNotes.add(p);
            } else if (p.getCategory() == PostCategory.POST) {
                if (p.isPublished()) {
                    publicPosts.add(p);
                } else {
                    privatePosts.add(p);
                }
            } else {
                // do nothing if category is Academy / Blog
            }
        }

        if (published) {
            return publicPosts;
        } else {
            if (category.equalsIgnoreCase(String.valueOf(PostCategory.NOTE))) {
                return privateNotes;
            } else if (category.equalsIgnoreCase(String.valueOf(PostCategory.POST))) {
                return privatePosts;
            } else {
                return null;
            }
        }
    }
    public List<Task> getTasks(String username, TaskType type) {
        User toFind = getUser( username );
        return toFind.getTasksByType(type);
    }
    public byte[] getProfileImg(String username) {
        User user = getUser(username);
        return user.getProfileImg();
    }

    public HashMap<Garden, ArrayList<UserResponse>> getGardens(String username) {
        User toFind = getUser( username );
        return toFind.getGardens();
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
        try {
            User user = getUser(username);
            ArrayList<String> changed = change(user, modified);
            repository.save(user);
            return changed;
        } catch (Exception e) {
            throw new BadRequestException("cannot update user " + username);
        }
    }

    public ArrayList<String> updateProfile(String username, UserRequest modified) {
        try {
            User user = getUser(username);
            ArrayList<String> changed = new ArrayList<>();
            // change display name
            if (!Objects.equals(modified.getName(), user.getName())) {
                // display name has changed
                user.setName(modified.getName());
                changed.add("changed display name");
            }
            // change email
            if (!Objects.equals(modified.getEmail(), user.getEmail())) {
                // email has changed
                user.setEmail(modified.getEmail());
                changed.add("changed email");
            }
            // change birthday
            if (modified.getBirthday() != user.getBirthday()) {
                // birthday has changed
                user.setBirthday(modified.getBirthday());
                changed.add("changed birthday");
            }
            // change province
            if (modified.getProvince() != user.getProvince()) {
                // province has changed
                user.setProvince(modified.getProvince());
                changed.add("changed province");
            }
            repository.save(user);
            return changed;
        } catch (Exception e) {
            throw new BadRequestException("cannot update user " + username);
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
            String out = user.setUserXp(xp);
            repository.save(user);
            return out;
        } catch (Exception e) {
            throw new InvalidException("set xp to user " + username);
        }
    }
    public String setLastActivity(String username, LocalDate date) {
        User user = getUser( username );
        try {
            user.setLastActivity(date);
            repository.save(user);
            return "last activity = " + user.getLastActivity();
        } catch (Exception e) {
            throw new InvalidException("last activity");
        }
    }
    public List<Post> addPost(String username, Map<String, Object> fields) {
        User user = getUser( username );
        PostRequest toAdd = new PostRequest();
        try {
            toAdd.setAuthor(user);
            toAdd.setTitle((String) fields.get("title"));
            toAdd.setSummary((String) fields.get("summary"));
            toAdd.setDescription((String) fields.get("description"));
            toAdd.setPublished((boolean) fields.get("published"));
            toAdd.setCategory((String) fields.get("category"));
            toAdd.setCreated(LocalDateTime.now());

            user.addPost(toAdd.convert());
            repository.save(user);
            return user.getPosts();
        } catch (Exception e) {
            throw new InvalidException("post");
        }
    }
    public Task addTask(String username, Map<String, Object> fields) {
        User user = getUser( username );
        TaskRequest toAdd = new TaskRequest();
        try {
            toAdd.setOwner(user);
            toAdd.setType((String) fields.get("type"));
            toAdd.setTitle((String) fields.get("title"));
            toAdd.setDescription((String) fields.get("description"));
            toAdd.setDone((boolean) fields.get("done"));
            toAdd.setCreated(LocalDate.now());

            String date = (String) fields.get("deadline");
            LocalDate deadline = LocalDate.parse(date);

            toAdd.setDeadline(deadline);

            user.addTask(toAdd.convert());
            repository.save(user);
            return toAdd.convert();
        } catch (Exception e) {
            throw new InvalidException("task");
        }
    }
    public byte[] addProfileImage(String username, MultipartFile file) throws IOException {
        User user = getUser( username );
        String fileName = file.getOriginalFilename();
        byte[] data = file.getBytes();
        user.setProfileImg(data);
        return repository.save(user).getProfileImg();
    }

    // DELETE
    public void deleteUser(String username) {
        if (userExistsByUsername( username )) {
            repository.deleteById(username);
        } else {
            throw new UserNotFoundException(username);
        }
    }
    public String removeAuthority(String username, String authorityString) {
        User user = getUser( username );
        user.removeAuthority(authorityString);
        repository.save(user);

        if (user.getAuthorities().contains(authorityString)) throw new BadRequestException("cannot remove authority");
        return "removed " + authorityString + " from " + username;
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
