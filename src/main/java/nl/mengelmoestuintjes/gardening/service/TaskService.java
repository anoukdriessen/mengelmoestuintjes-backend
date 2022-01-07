//package nl.mengelmoestuintjes.gardening.service;
//
//import nl.mengelmoestuintjes.gardening.controller.exceptions.RecordNotFoundException;
//import nl.mengelmoestuintjes.gardening.model.tasks.Task;
//import nl.mengelmoestuintjes.gardening.model.tasks.TypeTask;
//import nl.mengelmoestuintjes.gardening.repository.TaskRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Objects;
//import java.util.Optional;
//
//@Service
//public class TaskService {
//    private static final String NOT_FOUND = "Task not found";
//    private final TaskRepository repository;
//    @Autowired
//    public TaskService(TaskRepository repository) {
//        this.repository = repository;
//    }
//
//    public Task newTask(Task toAdd) {
//        return repository.save( toAdd );
//    }
//
//    public Iterable<Task> getAll(boolean done, TypeTask type) {
//        boolean typeNotEmpty = !Objects.isNull(type);
//        if ( done ) {
//            if ( typeNotEmpty ) return repository.findByTypeAndDoneFalse( type );
//            return repository.findByDoneFalse();
//        }
//        if ( !Objects.isNull(type) ) return repository.findByType( type );
//
//        return repository.findAll();
//    }
//
//    public Task getById( long id ) {
//        Optional<Task> toFind = repository.findById(id);
//        if (toFind.isPresent()) {   // check if quote exists
//            return toFind.get();
//        } else {                    // post does not exists
//            throw new RecordNotFoundException(NOT_FOUND);
//        }
//    }
//
//    public void update( long id, Task modified ){
//        Task toModify = repository.findById( id ).orElse( null );
//        if (toModify != null) {
//            boolean typeNotNull = modified.getType() != null;
//            boolean titleNotNull = modified.getTitle() != null;
//            boolean descNotNull = modified.getDescription() != null;
//            boolean startingNotNull = modified.getStarting() != null;
//            boolean dueNotNull = modified.getDueDate() != null;
//            boolean pointsNotNull = modified.getPoints() != 0;
//
//            if ( typeNotNull ) toModify.setType( modified.getType() );
//            if ( titleNotNull ) toModify.setTitle( modified.getTitle() );
//            if ( descNotNull ) toModify.setDescription( modified.getDescription() );
//            if ( startingNotNull ) toModify.setStarting( modified.getStarting() );
//            if ( dueNotNull ) toModify.setDueDate( modified.getDueDate() );
//            if ( pointsNotNull ) toModify.setPoints( modified.getPoints() );
//
//            toModify.setIsDone( modified.getIsDone() );
//
//            repository.save(toModify);
//        } else {
//            throw new RecordNotFoundException(NOT_FOUND);
//        }
//    }
//
//    public Task delete( long id ) {
//        Optional<Task> toFind = repository.findById( id );
//        if (toFind.isPresent()) {  // check if task exists
//            Task toDelete = toFind.get();
//            repository.deleteById( id );
//            return toDelete;
//        } else {  // task does not exists
//            throw new RecordNotFoundException(NOT_FOUND);
//        }
//    }
//}
