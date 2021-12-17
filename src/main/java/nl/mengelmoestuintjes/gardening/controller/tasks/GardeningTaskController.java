package nl.mengelmoestuintjes.gardening.controller.tasks;

import nl.mengelmoestuintjes.gardening.controller.dto.task.GardeningTaskRequestDto;
import nl.mengelmoestuintjes.gardening.controller.dto.task.GardeningTaskResponseDto;
import nl.mengelmoestuintjes.gardening.model.tasks.GardeningTask;
import nl.mengelmoestuintjes.gardening.service.tasks.GardeningTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("tasks/gardening")
@CrossOrigin
public class GardeningTaskController {
    private final GardeningTaskService taskService;

    @Autowired
    public GardeningTaskController(GardeningTaskService taskService) {
        this.taskService = taskService;
    }

    // Create
    @PostMapping
    public GardeningTaskResponseDto newTask(@Valid @RequestBody GardeningTaskRequestDto toAdd ) {
        GardeningTask task = taskService.newTask( toAdd.toTask() );
        return GardeningTaskResponseDto.fromTask(task);
    }

    // Read
    @GetMapping
    public List<GardeningTaskResponseDto> getAllTasks(
            @RequestParam(name = "done", required = false) boolean done,
            @RequestParam(name = "month", defaultValue = "0", required = false) int month
    ) {
        List<GardeningTaskResponseDto> all = new ArrayList<>();
        Iterable<GardeningTask> tasks = taskService.getAllTasks(done, month);

        for (GardeningTask t : tasks) {
            all.add( GardeningTaskResponseDto.fromTask( t ) );
        }
        return all;
    }

    @GetMapping(value = "/{id}")
    public GardeningTaskResponseDto getTaskById(@PathVariable( "id" ) int id) {
        GardeningTask task = taskService.getTaskById(id);
        return GardeningTaskResponseDto.fromTask(task);
    }

    // Update
    @PutMapping(value = "/{id}")
    public GardeningTaskResponseDto updateTask(@PathVariable( "id" ) int id, @RequestBody GardeningTask modifiedTask) {
        taskService.updateTask(id, modifiedTask);
        return GardeningTaskResponseDto.fromTask(modifiedTask);
    }

    // Delete
    @DeleteMapping(value = "/{id}")
    public void deleteTaskById(@PathVariable( "id" ) int id) {
        taskService.deleteTaskById(id);
    }
}
