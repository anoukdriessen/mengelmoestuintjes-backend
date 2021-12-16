package nl.mengelmoestuintjes.gardening.controller;

import nl.mengelmoestuintjes.gardening.controller.dto.task.ToDoTaskRequestDto;
import nl.mengelmoestuintjes.gardening.controller.dto.task.ToDoTaskResponseDto;
import nl.mengelmoestuintjes.gardening.model.tasks.ToDoTask;
import nl.mengelmoestuintjes.gardening.service.tasks.ToDoTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("tasks/todo")
@CrossOrigin
public class ToDoTaskController {
    private final ToDoTaskService taskService;

    @Autowired
    public ToDoTaskController(ToDoTaskService taskService) {
        this.taskService = taskService;
    }

    // Create
    @PostMapping
    public ToDoTaskResponseDto newTask(@Valid @RequestBody ToDoTaskRequestDto toAdd ) {
        ToDoTask task = taskService.newTask( toAdd.toTask() );
        return ToDoTaskResponseDto.fromTask(task);
    }

    // Read
    @GetMapping
    public List<ToDoTaskResponseDto> getAllTasks(
            @RequestParam(name = "done", required = false) boolean done,
            @RequestParam(name = "date", required = false) Date date
    ) {
        List<ToDoTaskResponseDto> all = new ArrayList<>();
        Iterable<ToDoTask> tasks = taskService.getAllTasks(done, date);

        for (ToDoTask t : tasks) {
            all.add( ToDoTaskResponseDto.fromTask(t) );
        }
        return all;
    }

    @GetMapping(value = "/{id}")
    public ToDoTaskResponseDto getTaskById(@PathVariable( "id" ) int id) {
        ToDoTask task = taskService.getTaskById(id);
        return ToDoTaskResponseDto.fromTask(task);
    }

    // Update
    @PutMapping(value = "/{id}")
    public ToDoTaskResponseDto updateTask(@PathVariable( "id" ) int id, @RequestBody ToDoTask modifiedTask) {
        taskService.updateTask(id, modifiedTask);
        return ToDoTaskResponseDto.fromTask(modifiedTask);
    }

    // Delete
    @DeleteMapping(value = "/{id}")
    public void deleteTaskById(@PathVariable( "id" ) int id) {
        taskService.deleteTaskById(id);
    }
}
