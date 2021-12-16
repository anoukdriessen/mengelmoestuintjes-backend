package nl.mengelmoestuintjes.gardening.service.tasks;

import nl.mengelmoestuintjes.gardening.exceptions.RecordNotFoundException;
import nl.mengelmoestuintjes.gardening.model.tasks.GardeningTask;
import nl.mengelmoestuintjes.gardening.repository.GardeningTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GardeningTaskService {
    private static final String NOT_FOUND = "Task not found";
    private final GardeningTaskRepository taskRepository;

    @Autowired
    public GardeningTaskService(GardeningTaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Create
    public GardeningTask newTask(GardeningTask toAdd) {
        return taskRepository.save( toAdd );
    }

    // Read
    public Iterable<GardeningTask> getAllTasks(boolean done, int month) {
        if (month != 0) {
            return taskRepository.findAllByMonth(month);
        } else if ( done ) {
            return taskRepository.findByDoneTrue();
        } else {
            return taskRepository.findAll();
        }
    }

    public GardeningTask getTaskById(int id) {
        Optional<GardeningTask> toFind = taskRepository.findById(id);

        if (toFind.isPresent()) { // check if post exists
            return toFind.get();
        } else {  // post does not exists
            throw new RecordNotFoundException(NOT_FOUND);
        }
    }

    // Update
    public void updateTask(int id, GardeningTask modified) {
        GardeningTask toModify = taskRepository.findById(id).orElse(null);

        if (toModify != null) {
            boolean titleNotEmpty = !modified.getTitle().isEmpty();
            boolean descriptionNotEmpty = !modified.getTitle().isEmpty();
            boolean doneChanged = modified.isDone() != toModify.isDone();
            boolean pointsNotEmpty = modified.getPoints() != 0;
            boolean monthNotEmpty = modified.getMonth() != 0;


            if (titleNotEmpty) {
                toModify.setTitle(modified.getTitle());
            }
            if (descriptionNotEmpty) {
                toModify.setDescription(modified.getDescription());
            }
            if (doneChanged) {
                toModify.setDone(modified.isDone());
            }
            if (pointsNotEmpty) {
                toModify.setPoints(modified.getPoints());
            }
            if (monthNotEmpty) {
                toModify.setMonth(toModify.getMonth());
            }

            // if something is modified set modified date
            taskRepository.save(toModify);
        } else {
            throw new RecordNotFoundException(NOT_FOUND);
        }
    }

    // Delete
    public void deleteTaskById(int id) {
        Optional<GardeningTask> toFind = taskRepository.findById(id);

        if (toFind.isPresent()) { // check if post exists
            taskRepository.deleteById(id);
        } else { // post does not exists
            throw new RecordNotFoundException(NOT_FOUND);
        }
    }
}
