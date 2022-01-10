package nl.mengelmoestuintjes.gardening.controller;

import nl.mengelmoestuintjes.gardening.controller.exceptions.BadRequestException;
import nl.mengelmoestuintjes.gardening.dto.request.TaskRequest;
import nl.mengelmoestuintjes.gardening.model.Task;
import nl.mengelmoestuintjes.gardening.model.TaskType;
import nl.mengelmoestuintjes.gardening.model.User;
import nl.mengelmoestuintjes.gardening.service.TaskService;
import nl.mengelmoestuintjes.gardening.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/taken")
//@CrossOrigin
public class TaskController {
    private TaskService service;
    private UserService userService;

    @Autowired
    public TaskController(TaskService taskService, UserService userService) {
        this.service = taskService;
        this.userService = userService;
    }

    // CREATE
    @PostMapping
    public Task newTask(
            @RequestParam(value = "username") String username,
            @RequestBody TaskRequest request
    ) {
        try {
            User owner = userService.getUser( username );
            request.setOwner( owner );
            return service.create( request );
        } catch (BadRequestException e) {
            throw new BadRequestException("cannot create task");
        }
    }

    // READ
    @GetMapping
    public Iterable<Task> getAllTasks(
            @RequestParam(name = "title", defaultValue = "", required = false) String title,
            @RequestParam(name = "type") TaskType type,
            @RequestParam(name = "done") boolean isDone
    ) {
        return service.getAll(title, type, isDone);
    }
    @GetMapping("/{id}")
    public Task getTaskById(
            @PathVariable( "id" ) long id
    ){
        return service.getTask(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTask(
            @PathVariable("id") long id,
            @RequestBody TaskRequest request
    ){
        return ResponseEntity.ok().body(service.updateTask(id, request));
    }
    @PutMapping("{id}/{done}")
    public ResponseEntity<Object> setIsDone(
            @PathVariable("id") long id,
            @PathVariable("done") boolean isDone
    ) {
        return ResponseEntity.ok().body(service.setIsDone(id, isDone));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(
            @PathVariable("id") long id
    ) {
        return ResponseEntity.ok().body(service.delete(id));
    }

}
