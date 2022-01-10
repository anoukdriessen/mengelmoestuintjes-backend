package nl.mengelmoestuintjes.gardening.controller;

import nl.mengelmoestuintjes.gardening.controller.exceptions.BadRequestException;
import nl.mengelmoestuintjes.gardening.dto.request.GardenRequest;
import nl.mengelmoestuintjes.gardening.model.User;
import nl.mengelmoestuintjes.gardening.model.garden.Garden;
import nl.mengelmoestuintjes.gardening.service.GardenService;
import nl.mengelmoestuintjes.gardening.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/tuintjes")
@CrossOrigin
public class GardenController {
    private GardenService service;
    private UserService userService;

    @Autowired
    public GardenController( GardenService service, UserService userService ) {
        this.service = service;
        this.userService = userService;
    }

    // CREATE
    @PostMapping
    public String newGarden(
            @RequestParam(value = "username") String username,
            @RequestBody GardenRequest request
    ) {
        try {
            User owner = userService.getUser(username);
            request.addOwner(owner);
            return service.create(request);
        } catch (BadRequestException e) {
            throw new BadRequestException("Cannot create garden");
        }
    }
    @PostMapping(value = "/{username}/{id}")
    public String addOwner(
            @PathVariable("username") String username,
            @PathVariable("id") long id
    ) {
        try {
            Garden garden = service.getGarden(id);
            User owner = userService.getUser(username);
            return service.addUser(owner, garden);
        } catch (Exception e) {
            throw new BadRequestException( e.getMessage() );
        }
    }

    // READ
    @GetMapping
    public Iterable<Garden> getAll() {
        return service.getAll();
    }
    @GetMapping(value = "/{id}")
    public Garden getGardenById(@PathVariable("id") long id) {
        return service.getGarden(id);
    }
    @GetMapping(value = "/{id}/gebruikers")
    public Iterable<String> getUsersFromGarden(@PathVariable("id") long id) {
        return service.getUsers(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateGarden(
            @PathVariable("id") long id,
            @RequestBody GardenRequest request
    ) {
        return ResponseEntity.ok().body(service.updateGarden(id, request));
    }
//    @PutMapping("/{id}/x/{x}")
//    public ResponseEntity<Object> updateX(
//            @PathVariable("id") long id,
//            @PathVariable("x") byte x
//    ) {
//        return ResponseEntity.ok().body(service.updateX(id, x));
//    }
//    @PutMapping("/{id}/y/{y}")
//    public ResponseEntity<Object> updateY(
//            @PathVariable("id") long id,
//            @PathVariable("y") byte y
//    ) {
//        return ResponseEntity.ok().body(service.updateY(id, y));
//    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(service.delete(id));
    }
    @DeleteMapping("/{username}/{id}")
    public String removeOwner(
            @PathVariable("username") String username,
            @PathVariable("id") long id
    ) {
        try {
            Garden garden = service.getGarden(id);
            User owner = userService.getUser(username);
            return service.removeUser(owner, garden);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
