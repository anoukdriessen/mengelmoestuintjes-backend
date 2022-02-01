package nl.mengelmoestuintjes.gardening.controller;

import nl.mengelmoestuintjes.gardening.controller.exceptions.BadRequestException;
import nl.mengelmoestuintjes.gardening.dto.request.GardenRequest;
import nl.mengelmoestuintjes.gardening.dto.request.PostRequest;
import nl.mengelmoestuintjes.gardening.dto.response.GardenResponse;
import nl.mengelmoestuintjes.gardening.dto.response.UserResponse;
import nl.mengelmoestuintjes.gardening.model.Post;
import nl.mengelmoestuintjes.gardening.model.Task;
import nl.mengelmoestuintjes.gardening.model.User;
import nl.mengelmoestuintjes.gardening.model.garden.Field;
import nl.mengelmoestuintjes.gardening.model.garden.Garden;
import nl.mengelmoestuintjes.gardening.service.GardenService;
import nl.mengelmoestuintjes.gardening.service.PostService;
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
    private PostService postService;

    @Autowired
    public GardenController( GardenService service, UserService userService, PostService postService ) {
        this.service = service;
        this.userService = userService;
        this.postService = postService;
    }

    // CREATE
    @PostMapping
    public Garden newGarden(
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
            throw new BadRequestException( "cannot add owner" );
        }
    }

    // READ
    @GetMapping(value = "/from/{username}")
    public Iterable<GardenResponse> getAllByUser(@PathVariable("username") String username) {
        User owner = userService.getUser(username);
        return service.findGardensByOwnersEquals(owner);
    }

    @GetMapping
    public Iterable<GardenResponse> getAll() {
        return service.getAll();
    }
    @GetMapping(value = "/{id}")
    public Garden getGardenById(@PathVariable("id") long id) {
        return service.getGarden(id);
    }
    @GetMapping(value = "/{id}/gebruikers")
    public Iterable<UserResponse> getUsersFromGarden(@PathVariable("id") long id) {
        return service.getUsers(id);
    }
    @GetMapping(value = "/{id}/taken")
    public Iterable<Task> getTasksFromGarden(@PathVariable("id") long id) { return service.getTasks(id); }
    @GetMapping(value = "/{id}/notities")
    public Iterable<Post> getPostsFromGarden(@PathVariable("id") long id) { return service.getPosts(id); }
    @GetMapping(value = "{id}/velden")
    public Iterable<Field> getFieldsFromGarden(@PathVariable("id") long id){
        return service.getFields(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateGarden(
            @PathVariable("id") long id,
            @RequestBody GardenRequest request
    ) {
        return ResponseEntity.ok().body(service.updateGarden(id, request));
    }
    @PutMapping("/{id}/size")
    public ResponseEntity<Object> updateSize(
            @PathVariable("id") long id,
            @RequestBody GardenRequest request
    ) {
        return ResponseEntity.ok().body(service.updateSize(id, request.getX(), request.getY()));
    }
    @PutMapping(value = "/{id}/taken")
    public Iterable<Task> addTasks(
            @PathVariable("id") long id
    ) {
        try {
            Garden garden = service.getGarden(id);
            return service.updateTasks(garden);
        } catch (Exception e) {
            throw new BadRequestException( "cannot update tasks ");
        }
    }
    @PutMapping(value = "/{id}/velden")
    public Field addField(
            @PathVariable("id") Long id,
            @RequestBody Field field
    ) {
        try {
            Garden garden = service.getGarden(id);
            service.addField(field, garden);
            return field;
        } catch (Exception e) {
            throw new BadRequestException( "cannot add post ");
        }
    }
    @PutMapping(value = "/{id}/{username}/notities")
    public String addPost(
            @PathVariable("id") Long id,
            @PathVariable("username") String username,
            @RequestBody PostRequest post
    ) {
        try {
            Garden garden = service.getGarden(id);
            User owner = userService.getUser(username);
            post.setAuthor(owner);
            service.addNote(garden, post);
            return post.getTitle() + " has been added";
        } catch (Exception e) {
            throw new BadRequestException( "cannot add post ");
        }
    }


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
    @DeleteMapping("/{id}/{username}/notitie/{note}")
    public String deletePost(
            @PathVariable("id") long id,
            @PathVariable("note") long note
    ) {
        try {
            Garden garden = service.getGarden(id);
            return service.deletePost(postService.getPost(note), garden);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
