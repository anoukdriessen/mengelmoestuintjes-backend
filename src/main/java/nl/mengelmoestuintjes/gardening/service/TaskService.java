package nl.mengelmoestuintjes.gardening.service;


import nl.mengelmoestuintjes.gardening.controller.exceptions.InvalidException;
import nl.mengelmoestuintjes.gardening.controller.exceptions.TaskNotFoundException;
import nl.mengelmoestuintjes.gardening.dto.request.TaskRequest;
import nl.mengelmoestuintjes.gardening.model.Task;
import nl.mengelmoestuintjes.gardening.model.TaskType;
import nl.mengelmoestuintjes.gardening.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {
    private TaskRepository repository;

    @Autowired
    public TaskService( TaskRepository repository ) {
        this.repository = repository;
    }

    // CREATE
    public Task create(TaskRequest toAdd) {
        Task newTask = new Task();

        newTask.setOwner( toAdd.getOwner() );
        newTask.setType( toAdd.getType() );
        newTask.setTitle( toAdd.getTitle() );
        newTask.setDone( toAdd.isDone() );
        newTask.setCreated();
        newTask.setDeadline( toAdd.getDeadline() );

        repository.save( newTask );
        return newTask;
    }

    // READ
    public Iterable<Task> getAll(String title, TaskType type, boolean isDone) {
        boolean hasTitle = !title.isBlank();

        if (hasTitle) return repository.findAllByTypeAndTitleContainingAndDone(type, title, isDone);

        return repository.findByTypeAndDone(type, isDone);
    }
    public Task getTask(long id) {
        Optional<Task> toFind = repository.findById( id );
        boolean taskFound = toFind.isPresent();
        if ( taskFound ) return toFind.get();
        throw new TaskNotFoundException(id);
    }

    // UPDATE
    public String updateTask(long id, TaskRequest modified) {
        Task task = getTask( id );

        task.setType( modified.getType() );
        task.setTitle( modified.getTitle() );
        task.setDone( modified.isDone() );
        task.setCreated();
        task.setDeadline( modified.getDeadline() );

        repository.save(task);
        return task.getTitle() + " has been updated";
    }
    public String setIsDone(long id, boolean isDone) {
        Task task = getTask( id );
        task.setDone(isDone);
        repository.save(task);
        return task.getTitle() + "is now done=" + isDone;
    }

    // DELETE
    public String delete(long id) {
        Task t = getTask(id);
        repository.deleteById(id);
        return t.getTitle() + " is deleted";
    }
}
