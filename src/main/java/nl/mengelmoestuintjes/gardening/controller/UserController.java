package nl.mengelmoestuintjes.gardening.controller;

import nl.mengelmoestuintjes.gardening.controller.exceptions.BadRequestException;
import nl.mengelmoestuintjes.gardening.dto.request.UserRequest;
import nl.mengelmoestuintjes.gardening.model.users.Province;
import nl.mengelmoestuintjes.gardening.model.users.User;
import nl.mengelmoestuintjes.gardening.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/gebruikers")
public class UserController {
    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public String create(
            @RequestBody User toAdd
    ) {
        return service.create( toAdd );
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

    // READ
    @GetMapping
    public ResponseEntity<Object> getUsers(
            @RequestParam(name = "email", defaultValue = "", required = false) String email,
            @RequestParam(name = "level", defaultValue = "", required = false) String level,
            @RequestParam(name = "province", defaultValue = "", required = false) Province province
    ) {
        return ResponseEntity.ok().body(service.getAll(email, level, province));
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<Object> getUser(
            @PathVariable("username") String username
    ) {
        return ResponseEntity.ok().body(service.getUser(username));
    }

    @GetMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> getUserAuthorities(
            @PathVariable("username") String username
    ) {
        return ResponseEntity.ok().body(service.getAuthorities(username));
    }

    @GetMapping(value = "/{username}/xp")
    public ResponseEntity<Object> getUserXpInfo(
            @PathVariable("username") String username
    ) {
        return ResponseEntity.ok().body(service.getXP(username));
    }

    @GetMapping(value = "/provincies")
    public ResponseEntity<Object> allProvinces() {
        List<String> all = new ArrayList<>();
        for (Province p : Province.values()) {
            all.add(p.name());
        }
        return ResponseEntity.ok().body( all );
    }
    @GetMapping(value = "/{username}/berichten")
    public ResponseEntity<Object> getUserPosts(
            @PathVariable("username") String username,
            @RequestParam(value = "published") boolean published
    ) {
        return ResponseEntity.ok().body(service.getPosts(username, published));
    }

    // UPDATE
    @PutMapping(value = "/{username}")
    public ArrayList<String> update(
            @PathVariable("username") String username,
            @RequestBody UserRequest user) {
        try {
            return service.update(username, user);
        } catch (Exception e) {
            throw new BadRequestException( e.getMessage() );
        }
    }
    @PatchMapping(value = "/{username}/password")
    public String setPassword(
            @PathVariable("username") String username,
            @RequestBody String password)
    {
        try {
            service.setPassword(username, password);
            return "Password changed for user " + username;
        } catch (Exception e) {
            throw new BadRequestException( e.getMessage() );
        }
    }
    @PatchMapping(value = "/{username}/birthday")
    public LocalDate setBirthday(
            @PathVariable("username") String username,
            @RequestBody String newBirthday
    ) {
        try {
            LocalDate birthday = LocalDate.parse(newBirthday);
            return service.setBirthday(username, birthday);
        } catch (Exception e) {
            throw new BadRequestException( e.getMessage() );
        }
    }
    @PatchMapping(value = "/{username}/xp/{num}")
    public String setXP(
            @PathVariable("username") String username,
            @PathVariable("num") Long toAdd
    ) {
        try {
            return service.setXP(username, toAdd);
        } catch (Exception e) {
            throw new BadRequestException( e.getMessage() );
        }
    }
    @PatchMapping(value = "/{username}/activity")
    public String setLastActivity(
            @PathVariable("username") String username
    ) {
        try {
            return service.setLastActivity(username);
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    // DELETE
    @DeleteMapping(value = "/{username}")
    public String delete(@PathVariable("username") String username) {
        service.deleteUser(username);
        return "user " + username + " is deleted";
    }

    @DeleteMapping(value = "/{username}/authorities/{authority}")
    public String deleteUserAuthority(@PathVariable("username") String username, @PathVariable("authority") String authority) {
        service.removeAuthority(username, authority);
        return "authority " + authority + " deleted from " + username + " ";
    }
}

