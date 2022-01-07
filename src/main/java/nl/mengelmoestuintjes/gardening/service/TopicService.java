//package nl.mengelmoestuintjes.gardening.service;
//
//import nl.mengelmoestuintjes.gardening.controller.exceptions.RecordNotFoundException;
//import nl.mengelmoestuintjes.gardening.model.academy.Category;
//import nl.mengelmoestuintjes.gardening.model.academy.Topic;
//import nl.mengelmoestuintjes.gardening.repository.TopicRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.Objects;
//import java.util.Optional;
//
//@Service
//public class TopicService {
//    private static final String NOT_FOUND = "topic not found";
//    private final TopicRepository repository;
//    @Autowired
//    public TopicService(TopicRepository repository) {
//        this.repository = repository;
//    }
//
//    public Topic newTopic(Topic toAdd) {
//        return repository.save( toAdd );
//    }
//
//    public Iterable<Topic> getAll() {
//        return repository.findAll();
//    }
//
//    public Topic getById( long id ) {
//        Optional<Topic> toFind = repository.findById(id);
//        if (toFind.isPresent()) {   // check if quote exists
//            return toFind.get();
//        } else {                    // post does not exists
//            throw new RecordNotFoundException(NOT_FOUND);
//        }
//    }
//
//    public void update( long id, Topic modified ){
//        Topic toModify = repository.findById( id ).orElse( null );
//        if (toModify != null) {
//            boolean categoryEmpty = Objects.isNull(modified.getCategory());
//            boolean titleEmpty = modified.getTitle().isEmpty();
//            boolean descrEmpty = modified.getDescription().isEmpty();
//            boolean isModified = false;
//            if ( !categoryEmpty ) {
//                toModify.setCategory( modified.getCategory());
//                isModified = true;
//            }
//            if ( !titleEmpty ) {
//                toModify.setTitle( modified.getTitle() );
//                isModified = true;
//            }
//            if ( !descrEmpty ) {
//                toModify.setDescription( modified.getDescription() );
//                isModified = true;
//            }
//            if ( isModified ) toModify.setModifiedAt( LocalDate.now() );
//            repository.save(toModify);
//        } else {
//            throw new RecordNotFoundException(NOT_FOUND);
//        }
//    }
//
//    public Topic delete( long id ) {
//        Optional<Topic> toFind = repository.findById( id );
//        if (toFind.isPresent()) {  // check if topic exists
//            Topic toDelete = toFind.get();
//            repository.delete( toDelete );
//            return toDelete;
//        } else {  // topic does not exists
//            throw new RecordNotFoundException(NOT_FOUND);
//        }
//    }
//}
