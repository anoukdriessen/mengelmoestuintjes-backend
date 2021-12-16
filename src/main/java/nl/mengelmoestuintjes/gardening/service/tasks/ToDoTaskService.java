package nl.mengelmoestuintjes.gardening.service.tasks;

import nl.mengelmoestuintjes.gardening.exceptions.RecordNotFoundException;
import nl.mengelmoestuintjes.gardening.model.tasks.ToDoTask;
import nl.mengelmoestuintjes.gardening.repository.ToDoTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class ToDoTaskService {
    private static final String NOT_FOUND = "Task not found";
    private final ToDoTaskRepository taskRepository;

    @Autowired
    public ToDoTaskService(ToDoTaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Create
    public ToDoTask newTask(ToDoTask toAdd) {
        return taskRepository.save( toAdd );
    }

    // Read
    public Iterable<ToDoTask> getAllTasks(boolean done, Date date) {
        if ( !Objects.isNull(date) ) {
//            Date today = new Date();
//            boolean hasExpired = date.before(today);
//            boolean isToday = date.equals(today);
//            LocalDateTime given = LocalDateTime.from(date.toInstant());
//            LocalDateTime tomorrow = LocalDateTime.from(today.toInstant()).plusDays(1);
//            boolean isTommorow = given.equals(tomorrow);
//
//            if ( hasExpired ) {
//                return taskRepository.findAllExpired(date);
//            } else if ( isToday ) {
//                return taskRepository.findAllToday(date);
//            } else if ( isTommorow ) {
//                return taskRepository.findAllTomorrow(date);
//            } else  {
//                return taskRepository.findAllSoon(date);
//            }
            return taskRepository.findAllByDueDate(date);
        } if ( done ) {
            return taskRepository.findByDoneTrue();
        } else {
            return taskRepository.findAll();
        }
    }

    public ToDoTask getTaskById(int id) {
        Optional<ToDoTask> toFind = taskRepository.findById(id);

        if (toFind.isPresent()) { // check if post exists
            return (ToDoTask) toFind.get();
        } else {  // post does not exists
            throw new RecordNotFoundException(NOT_FOUND);
        }
    }

    // Update
    public void updateTask(int id, ToDoTask modified) {
        ToDoTask toModify = (ToDoTask) taskRepository.findById(id).orElse(null);

        if (toModify != null) {
            boolean titleNotEmpty = !modified.getTitle().isEmpty();
            boolean descriptionNotEmpty = !modified.getTitle().isEmpty();
            boolean doneChanged = modified.isDone() != toModify.isDone();
            boolean dateNotEmpty = !Objects.isNull(modified.getDueDate());


            if (titleNotEmpty) {
                toModify.setTitle(modified.getTitle());
            }
            if (descriptionNotEmpty) {
                toModify.setDescription(modified.getDescription());
            }
            if (doneChanged) {
                toModify.setDone(modified.isDone());
            }
            if (dateNotEmpty) {
                toModify.setDueDate(modified.getDueDate());
            }

            // if something is modified set modified date
            taskRepository.save(toModify);
        } else {
            throw new RecordNotFoundException(NOT_FOUND);
        }
    }

    // Delete
    public void deleteTaskById(int id) {
        Optional<ToDoTask> toFind = taskRepository.findById(id);

        if (toFind.isPresent()) { // check if post exists
            taskRepository.deleteById(id);
        } else { // post does not exists
            throw new RecordNotFoundException(NOT_FOUND);
        }
    }
}
