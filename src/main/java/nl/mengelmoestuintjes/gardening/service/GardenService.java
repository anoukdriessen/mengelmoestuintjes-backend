//package nl.mengelmoestuintjes.gardening.service;
//
//import nl.mengelmoestuintjes.gardening.controller.exceptions.RecordNotFoundException;
//import nl.mengelmoestuintjes.gardening.model.garden.Garden;
//import nl.mengelmoestuintjes.gardening.repository.GardenRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class GardenService {
//
//    private static final String NOT_FOUND = "garden not found";
//    private final GardenRepository repository;
//    @Autowired
//    public GardenService(GardenRepository repository) {
//        this.repository = repository;
//    }
//
//    public Garden newGarden(Garden toAdd) {
//        return repository.save( toAdd );
//    }
//
//    public Iterable<Garden> getAll() {
//        return repository.findAll();
//    }
//
//    public Garden getById( long id ) {
//        Optional<Garden> toFind = repository.findById(id);
//        if (toFind.isPresent()) {   // check if quote exists
//            return toFind.get();
//        } else {                    // post does not exists
//            throw new RecordNotFoundException(NOT_FOUND);
//        }
//    }
//
//    public void update( long id, Garden modified ){
//        Garden toModify = repository.findById( id ).orElse( null );
//        if (toModify != null) {
//            boolean nameEmpty = modified.getName().isEmpty();
//            boolean xEmpty = modified.getX() == 0;
//            boolean yEmpty = modified.getY() == 0;
//
//            if ( !nameEmpty ) toModify.setName (modified.getName() );
//            if ( !xEmpty ) toModify.setX( modified.getX() );
//            if ( !yEmpty ) toModify.setY( modified.getY() );
//
//            repository.save(toModify);
//        } else {
//            throw new RecordNotFoundException(NOT_FOUND);
//        }
//    }
//
//    public Garden delete( long id ) {
//        Optional<Garden> toFind = repository.findById( id );
//        if (toFind.isPresent()) {  // check if garden exists
//            Garden toDelete = toFind.get();
//            repository.delete( toDelete );
//            return toDelete;
//        } else {  // garden does not exists
//            throw new RecordNotFoundException(NOT_FOUND);
//        }
//    }
//}
