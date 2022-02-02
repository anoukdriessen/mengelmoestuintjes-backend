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

    private final UserRepository repository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    private String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }
    public List<String> getAllPossibleProvinces() {
        List<String> all = new ArrayList<>();
        for (Province p : Province.values()) {
            all.add(p.name());
        }
        return all;
    }

    // CHECK
    public boolean userExistsByUsername(String username) {
        return repository.existsById(username);
    }
    public boolean userExistsByEmail(String email) {
        return repository.existsByEmail(email);
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

    // CREATE
    public User create(User toAdd) {
        // 1. create a request
        UserRequest request = new UserRequest();
        // 2. set values of toAdd to request
        request.setUsername( toAdd.getUsername() );
        request.setPassword( toAdd.getPassword() );
        request.setEmail( toAdd.getEmail() );
        // 3. encrypt password
        try {
            String encryptedPassword = passwordEncoder.encode( request.getPassword() );
            request.setPassword(encryptedPassword);
        } catch (Exception e) {
            throw new BadRequestException("password is invalid");
        }
        // 4. check if username / email exists
        if (userExistsByUsername( request.getUsername() ) || userExistsByEmail( request.getEmail() )) {
            throw new BadRequestException("username or email already exists");
        }
        // 5. create a user
        User newUser = request.toUser();
        newUser.setDefaultValues();
        newUser.addAuthority("ROLE_USER");

        // 6. save user to database
        repository.save(newUser);
        // 7. return the new user
        return newUser;
    }

    // READ
//    public Iterable<User> getAll( String email, String level, Province province ) {
    public Iterable<User> getAll( Province province ) {
        try {
//            boolean hasEmail = !email.isBlank();
//            boolean hasLevel = !level.isBlank();
            boolean hasProvince = province != null;

//            if (hasEmail) return repository.findAllByEmailContaining(email);
//            if (hasLevel) return repository.findByLevel(level);
            if (hasProvince) return repository.findAllByProvince(province);

            return repository.findAll();
        } catch (Exception e) { throw new BadRequestException(e.getMessage()); }
    }

    public HashMap<String, byte[]> getAllProfiles() {
        HashMap<String, byte[]> all = new HashMap<>();
//        Iterable<User> users = getAll("", "", null);
        Iterable<User> users = getAll(null);
        users.forEach(user -> {
            all.put( user.getUsername(), user.getProfileImg() );});
        return all;
    }

    public User getUser(String username) {
        Optional<User> toFind = repository.findById( username );
        boolean userFound = toFind.isPresent();

        if ( userFound ) {
            User found = toFind.get();
            found.setLastActivity(LocalDate.now());
            return found;
        } else { throw new UserNotFoundException(username); }
    }

    public UserResponse getUsersProfile(String username) {
        User found = getUser( username );
        return found.getUserProfile();
    }

    public List<String> getAuthorities(String username) {
        User found = getUser( username );
        return found.getAuthorities();
    }

    public String[] getXP(String username) {
        User toFind = getUser( username );
        String[] stats = new String[3];
        stats[0] = "" + toFind.getLevel();
        stats[1] = "" + toFind.getXp();
        stats[2] = "" + toFind.getLevelUpLimit();
        return stats;
    }

    public Iterable<String> getWithBirthdayToday() {
//        Iterable<User> all = getAll("", "", null);
        Iterable<User> all = getAll(null);
        ArrayList<String> isBirthday = new ArrayList<>();
        for (User u : all) {
            if (u.getBirthday() != null) {
                if (u.birthdayIsToday()) {
                    isBirthday.add(u.getUsername());
                }
            }
        }
        return isBirthday;
    }

    public List<Post> getPosts(String username, String category, boolean published) {
        User found = getUser( username );

        ArrayList<Post> privateNotes = new ArrayList<>();
        ArrayList<Post> privatePosts = new ArrayList<>();
        ArrayList<Post> publicPosts = new ArrayList<>();

        for (Post p : found.getPosts()) {
            if (p.getCategory() == PostCategory.NOTE) { // post is a private note
                privateNotes.add(p);
            } else if (p.getCategory() == PostCategory.POST) {
                if (p.isPublished()) { publicPosts.add(p);
                } else { privatePosts.add(p); }
            }
            // do nothing if category is Academy / Blog
        }

        if (published) { return publicPosts;
        } else {
            boolean isNote = category.equalsIgnoreCase(String.valueOf(PostCategory.NOTE));
            boolean isPost = category.equalsIgnoreCase(String.valueOf(PostCategory.POST));

            if (isNote) { return privateNotes;
            } else if (isPost) { return privatePosts;
            } else { return null; }
        }
    }

    public List<Task> getTasks(String username, TaskType type) {
        User found = getUser( username );
        return found.getTasksByType( type );
    }

    public byte[] getProfileImg(String username) {
        User found = getUser(username);
        return found.getProfileImg();
    }

    public HashMap<Garden, ArrayList<UserResponse>> getGardens(String username) {
        User found = getUser( username );
        return found.getGardens();
    }

    // UPDATE
    public List<String> addAuthority(String username, String authorityString) {
        User found = getUser( username );
        found.addAuthority(authorityString);
        repository.save(found);
        return found.getAuthorities();
    }

    public User change(User toChange, UserRequest changed){

        toChange.setPassword( passwordEncoder.encode( changed.getPassword()) );

        boolean enabled = toChange.isEnabled() != changed.isEnabled();
        boolean email = !toChange.getEmail().equalsIgnoreCase(changed.getEmail());
        boolean name = !toChange.getName().equalsIgnoreCase(changed.getName());
        boolean province = toChange.getProvince() != changed.getProvince();

        if (enabled) { toChange.setEnabled(changed.isEnabled()); }
        if (email) { toChange.setEmail(changed.getEmail()); }
        if (name) { toChange.setName(changed.getName()); }
        if (province) { toChange.setProvince(changed.getProvince()); }

        return toChange;
    }

    public User update(String username, UserRequest modified) {
        try {
            User user = getUser(username);
            User changed = change(user, modified);
            repository.save(user);
            return changed;
        } catch (Exception e) { throw new BadRequestException("cannot update user " + username); }
    }

    public User updateProfile(String username, UserRequest modified) {
        try {
            User found = getUser(username);

            boolean name = !Objects.equals(modified.getName(), found.getName());
            boolean email = !Objects.equals(modified.getEmail(), found.getEmail());
            boolean birthday = modified.getBirthday() != found.getBirthday();
            boolean province = modified.getProvince() != found.getProvince();

            if (name) { found.setName(modified.getName()); }
            if (email) { found.setEmail(modified.getEmail()); }
            if (birthday) { found.setBirthday(modified.getBirthday()); }
            if (province) { found.setProvince(modified.getProvince()); }
            repository.save(found);
            return found;
        } catch (Exception e) { throw new BadRequestException("cannot update user " + username); }
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
        } catch (Exception e) { throw new InvalidException("birthday"); }
    }

    public String setXP(String username, long toAdd) {
        User user = getUser(username);
        try {
            String xp = "" + toAdd;
            repository.save(user);
            return xp;
        } catch (Exception e) { throw new BadRequestException("cannot set xp to user " + username); }
    }

    public String setLastActivity(String username, LocalDate date) {
        User found = getUser( username );
        try {
            found.setLastActivity(date);
            repository.save(found);
            return found.getLastActivity().toString();
        } catch (Exception e) { throw new InvalidException("last activity"); }
    }

    public List<Post> addPost(String username, Map<String, Object> fields) {
        User found = getUser( username );

        PostRequest toAdd = new PostRequest();
        try {
            toAdd.setAuthor(found);
            toAdd.setTitle((String) fields.get("title"));
            toAdd.setSummary((String) fields.get("summary"));
            toAdd.setDescription((String) fields.get("description"));
            toAdd.setPublished((boolean) fields.get("published"));
            toAdd.setCategory((String) fields.get("category"));
            toAdd.setCreated(LocalDateTime.now());

            found.addPost(toAdd.convert());
            repository.save(found);
            return found.getPosts();
        } catch (Exception e) { throw new BadRequestException(); }
    }

    public Task addTask(String username, Map<String, Object> fields) {
        User found = getUser( username );

        TaskRequest toAdd = new TaskRequest();
        try {
            toAdd.setOwner(found);
            toAdd.setType((String) fields.get("type"));
            toAdd.setTitle((String) fields.get("title"));
            toAdd.setDescription((String) fields.get("description"));
            toAdd.setDone((boolean) fields.get("done"));
            toAdd.setCreated(LocalDate.now());

            String date = (String) fields.get("deadline");
            LocalDate deadline = LocalDate.parse(date);
            toAdd.setDeadline(deadline);

            found.addTask(toAdd.convert());
            repository.save(found);
            return toAdd.convert();
        } catch (Exception e) { throw new InvalidException("task"); }
    }

    public byte[] addProfileImage(String username, MultipartFile file) throws IOException {
        User found = getUser( username );
//        String fileName = file.getOriginalFilename();
        byte[] data = file.getBytes();
        found.setProfileImg(data);
        return repository.save(found).getProfileImg();
    }

    // DELETE
    public void deleteUser(String username) {
        if (userExistsByUsername( username )) { repository.deleteById(username);
        } else { throw new UserNotFoundException(username); }
    }

    public String removeAuthority(String username, String authorityString) {
        User found = getUser( username );
        found.removeAuthority(authorityString);
        repository.save(found);

        if (found.getAuthorities().contains(authorityString)) throw new BadRequestException("cannot remove authority");
        return authorityString;
    }

}
