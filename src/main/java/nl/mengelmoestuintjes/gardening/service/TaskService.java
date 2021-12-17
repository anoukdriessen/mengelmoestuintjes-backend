package nl.mengelmoestuintjes.gardening.service;

import nl.mengelmoestuintjes.gardening.exceptions.RecordNotFoundException;
import nl.mengelmoestuintjes.gardening.model.tasks.Task;
import nl.mengelmoestuintjes.gardening.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class TaskService {
    private static final String NOT_FOUND = "Task not found";
    private final TaskRepository repository;
    @Autowired
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task newTask(Task toAdd) {
        return repository.save( toAdd );
    }

    public Iterable<Task> getAll(boolean done) {
        if ( !done ) return repository.findByDone(done);

        return repository.findAll();
    }

    public Task getById( long id ) {
        Optional<Task> toFind = repository.findById(id);
        if (toFind.isPresent()) {   // check if quote exists
            return toFind.get();
        } else {                    // post does not exists
            throw new RecordNotFoundException(NOT_FOUND);
        }
    }

    public void update( long id, Task modified ){
        Task toModify = repository.findById( id ).orElse( null );
        if (toModify != null) {
            // check if empty
            // set attributes to toModify
            repository.save(toModify);
        } else {
            throw new RecordNotFoundException(NOT_FOUND);
        }
    }

    public void delete( long id ) {
        Optional<Task> toFind = repository.findById( id );
        if (toFind.isPresent()) {  // check if quote exists
            repository.deleteById( id );
        } else {  // post does not exists
            throw new RecordNotFoundException(NOT_FOUND);
        }
    }
}
