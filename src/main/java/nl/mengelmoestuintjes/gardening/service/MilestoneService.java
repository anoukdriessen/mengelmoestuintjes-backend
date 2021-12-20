package nl.mengelmoestuintjes.gardening.service;

import nl.mengelmoestuintjes.gardening.exceptions.RecordNotFoundException;
import nl.mengelmoestuintjes.gardening.model.Milestone;
import nl.mengelmoestuintjes.gardening.repository.MilestoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class MilestoneService {
    private static final String NOT_FOUND = "milestone not found";
    private final MilestoneRepository repository;
    @Autowired
    public MilestoneService(MilestoneRepository repository) {
        this.repository = repository;
    }

    public Milestone newMilestone(Milestone toAdd) {
        return repository.save( toAdd );
    }

    public Iterable<Milestone> getAll() {
        return repository.findAll();
    }

    public Milestone getById( long id ) {
        Optional<Milestone> toFind = repository.findById(id);
        if (toFind.isPresent()) {   // check if quote exists
            return toFind.get();
        } else {                    // post does not exists
            throw new RecordNotFoundException(NOT_FOUND);
        }
    }

    public void update( long id, Milestone modified ){
        Milestone toModify = repository.findById( id ).orElse( null );
        if (toModify != null) {
            boolean emptyTitle = modified.getTitle().isEmpty();
            boolean emptyUrl = modified.getUrlToBadge().isEmpty();
            boolean emptyPoints = modified.getPoints() == 0;
            boolean emptyOwner = Objects.isNull( modified.getOwner() );

            if ( !emptyTitle ) toModify.setTitle( modified.getTitle() );
            if ( !emptyUrl ) toModify.setUrlToBadge( modified.getUrlToBadge() );
            if ( !emptyPoints ) toModify.setPoints( modified.getPoints() );
            if ( !emptyOwner ) toModify.setOwner( modified.getOwner() );

            repository.save(toModify);
        } else {
            throw new RecordNotFoundException(NOT_FOUND);
        }
    }

    public void delete( long id ) {
        Optional<Milestone> toFind = repository.findById( id );
        if (toFind.isPresent()) {  // check if quote exists
            repository.delete( toFind.get() );
        } else {  // post does not exists
            throw new RecordNotFoundException(NOT_FOUND);
        }
    }

}
