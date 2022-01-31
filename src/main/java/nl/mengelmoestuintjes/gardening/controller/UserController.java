package nl.mengelmoestuintjes.gardening.controller;

import nl.mengelmoestuintjes.gardening.controller.exceptions.BadRequestException;
import nl.mengelmoestuintjes.gardening.controller.exceptions.InvalidException;
import nl.mengelmoestuintjes.gardening.dto.request.UserRequest;
import nl.mengelmoestuintjes.gardening.dto.response.UserResponse;
import nl.mengelmoestuintjes.gardening.model.*;
import nl.mengelmoestuintjes.gardening.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/gebruikers")
@CrossOrigin
public class UserController {

//    @Value("${app.uploads}")
//    private String storageLocation;

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Object> create( @RequestBody User toAdd ) {
        return ResponseEntity.ok().body(service.create( toAdd ));
    }

    @PostMapping(value = "/{username}/authorities")
    public String addUserAuthority(
            @PathVariable("username") String username,
            @RequestBody Map<String, Object> fields
    ) {
        try {
            String authorityName = (String) fields.get("authority");
            return service.addAuthority(username, authorityName);
        } catch (Exception e) {
            throw new BadRequestException( e.getMessage() );
        }
    }
    @PostMapping(value = "/{username}/berichten")
    public Post addUserPost(
            @PathVariable("username") String username,
            @RequestBody Map<String, Object> fields
    ) {
        try {
            return service.addPost(username, fields);
        } catch (Exception e) {
            throw new BadRequestException( e.getMessage() );
        }
    }
    @PostMapping(value = "/{username}/taken")
    public Task addUserTask(
            @PathVariable("username") String username,
            @RequestBody Map<String, Object> fields
    ) {
        try {
            return service.addTask(username, fields);
        } catch (Exception e) {
            throw new BadRequestException( e.getMessage() );
        }
    }

    @CrossOrigin
    @PostMapping(value = "/{username}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addUserProfileImage(
            @PathVariable("username") String username,
            @RequestParam(value="photo") MultipartFile multipartFile
    ) throws IOException {
        String out = "";
        try {
            service.addProfileImage(username, multipartFile);
            out = "Upload SUCCESS: " + multipartFile.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(out);
        } catch (Exception e) {
            out = "Upload ERROR: could not upload file " + multipartFile.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(out);
        }
    }

    // READ
    @GetMapping
    public Iterable<User> getUsers(
            @RequestParam(name = "email", defaultValue = "", required = false) String email,
            @RequestParam(name = "level", defaultValue = "", required = false) String level,
            @RequestParam(name = "province", defaultValue = "", required = false) Province province
    ) {
        return service.getAll(email, level, province);
    }
    @GetMapping(value = "/profiles")
    public HashMap<String, byte[]> getUsersProfile(){
        return service.getAllProfiles();
    }
    @GetMapping(value = "/{username}")
    public User getUser( @PathVariable("username") String username ) {
        return service.getUser(username);
    }
    @GetMapping(value = "/profile/{username}")
    public UserResponse getUsersProfile( @PathVariable("username") String username) { return service.getUsersProfile(username);}
    @GetMapping(value = "/check/username/{username}")
    public boolean isUserByUsername( @PathVariable("username") String username ) {
        return service.userExistsByUsername(username);
    }
    @GetMapping(value = "/check/email/{email}")
    public boolean isUserByEmail( @PathVariable("email") String email ) {
        return service.userExistsByEmail(email);
    }

    @GetMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> getUserAuthorities( @PathVariable("username") String username ) {
        return ResponseEntity.ok().body(service.getAuthorities(username));
    }
    @GetMapping(value = "/{username}/xp")
    public ResponseEntity<Object> getUserXpInfo( @PathVariable("username") String username ) {
        return ResponseEntity.ok().body(service.getXP(username));
    }
    @GetMapping(value = "/provincies")
    public ResponseEntity<Object> allProvinces() {
        return ResponseEntity.ok().body( service.getProvinces() );
    }
    @GetMapping(value = "/birthdays")
    public Iterable<String> allUsersWhosBirthdayToday() {
        return service.getWithBirthdayToday();
    }

    @GetMapping(value = "/{username}/berichten")
    public ResponseEntity<Object> getUserPosts(
            @PathVariable("username") String username,
            @RequestParam(value = "published") boolean published
    ) {
        return ResponseEntity.ok().body(service.getPosts(username, published));
    }
    @GetMapping(value = "/{username}/taken/{type}")
    public ResponseEntity<Object> getUserTaken(
            @PathVariable("username") String username,
            @PathVariable("type") TaskType type
    ) {
        return ResponseEntity.ok().body(service.getTasks(username, type));
    }
    @GetMapping(value = "/{username}/img")
    public ResponseEntity<Object> getUserProfile(
            @PathVariable("username") String username
    ) {
        return ResponseEntity.ok().body(service.getProfileImg(username));
    }

    // UPDATE
    @PutMapping(value = "/{username}")
    public ArrayList<String> update(
            @PathVariable("username") String username,
            @RequestBody UserRequest user) {
        return service.update(username, user);
    }
    @PatchMapping(value = "/{username}")
    public ArrayList<String> updateProfile(
            @PathVariable("username") String username,
            @RequestBody UserRequest user
    ) {
        return service.updateProfile(username, user);
    }
    @PatchMapping(value = "/{username}/password")
    public String setPassword(
            @PathVariable("username") String username,
            @RequestBody String password)
    {
        service.setPassword(username, password);
        return "Password changed for user " + username;
    }
    @PatchMapping(value = "/{username}/birthday")
    public LocalDate setBirthday(
            @PathVariable("username") String username,
            @RequestBody String newBirthday
    ) {
        LocalDate birthday;
        try {
            birthday = LocalDate.parse(newBirthday);
        } catch (Exception e) {
            throw new InvalidException();
        }
        return service.setBirthday(username, birthday);
    }
    @PatchMapping(value = "/{username}/xp/{num}")
    public String setXP(
            @PathVariable("username") String username,
            @PathVariable("num") Long toAdd
    ) {
        return service.setXP(username, toAdd);
    }
    @PatchMapping(value = "/{username}/activity")
    public String setLastActivity(
            @PathVariable("username") String username,
            @RequestBody String newActivity
    ) {
        LocalDate activity;
        try {
            activity = LocalDate.parse(newActivity);
        } catch (Exception e) {
            throw new InvalidException();
        }
        return service.setLastActivity(username, activity);
    }


    // DELETE
    @DeleteMapping(value = "/{username}")
    public String delete(@PathVariable("username") String username) {
        service.deleteUser(username);
        return "user " + username + " is deleted";
    }

    @DeleteMapping(value = "/{username}/authorities/{authority}")
    public String deleteUserAuthority(@PathVariable("username") String username, @PathVariable("authority") String authority) {
        return service.removeAuthority(username, authority);
    }
}

