package nl.mengelmoestuintjes.gardening.controller;

import nl.mengelmoestuintjes.gardening.controller.dto.TaskRequestDto;
import nl.mengelmoestuintjes.gardening.controller.dto.TaskResponseDto;
import nl.mengelmoestuintjes.gardening.model.posts.PostCategory;
import nl.mengelmoestuintjes.gardening.model.tasks.Task;
import nl.mengelmoestuintjes.gardening.model.tasks.TypeTask;
import nl.mengelmoestuintjes.gardening.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/tasks")
@CrossOrigin
public class TaskController {
    private final TaskService service;
    @Autowired
    public TaskController( TaskService service ) {
        this.service = service;
    }

    @PostMapping
    public TaskResponseDto newType( @Valid @RequestBody TaskRequestDto toAdd ) {
        Task task = service.newTask( toAdd.toTask() );
        return TaskResponseDto.fromTask( task );
    }

    @GetMapping
    public List<TaskResponseDto> getAll(
            @RequestParam(name = "done", required = false) boolean done,
            @RequestParam(name = "type", required = false) TypeTask type
    ) {
        List<TaskResponseDto> all = new ArrayList<>();
        Iterable<Task> tasks = service.getAll( done, type );


        for ( Task t : tasks) {
            all.add(
                    TaskResponseDto.fromTask( t )
            );
        }

        return all;
    }

    @GetMapping(value = "/{id}")
    public TaskResponseDto getById( @PathVariable( "id" ) long id) {
        Task task = service.getById( id );
        return TaskResponseDto.fromTask( task );
    }

    @PutMapping(value = "/{id}")
    public TaskResponseDto update( @PathVariable( "id" ) long id, @RequestBody Task modified ) {
        service.update( id, modified );
        return TaskResponseDto.fromTask( modified );
    }

    @DeleteMapping(value = "/{id}")
    public TaskResponseDto delete( @PathVariable( "id" ) long id ) {
        return TaskResponseDto.fromTask( service.delete( id ) );
    }



}
