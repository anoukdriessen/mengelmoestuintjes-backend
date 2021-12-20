package nl.mengelmoestuintjes.gardening.service;

import nl.mengelmoestuintjes.gardening.exceptions.RecordNotFoundException;
import nl.mengelmoestuintjes.gardening.model.Quote;
import nl.mengelmoestuintjes.gardening.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class QuoteService {
    private static String NOT_FOUND = "Quote not found";
    private final QuoteRepository quoteRepository;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public Quote newQuote(Quote toAdd) {
        return quoteRepository.save( toAdd );
    }

    public Iterable<Quote> getAll() {
        return quoteRepository.findAll();
    }

    public Quote getById( long id ) {
        Optional<Quote> toFind = quoteRepository.findById( id );
        if (toFind.isPresent()) {  // check if quote exists
            return toFind.get();
        } else { // quote does not exists
            throw new RecordNotFoundException(NOT_FOUND);
        }
    }

    public Quote getRandomQuote() {
        long bound = quoteRepository.count();
        Random r = new Random();
        int id = r.nextInt( ( int ) bound - 1);
        return getById( id );
    }

    public void updateQuote( long id, Quote modified){
        Quote toModify = quoteRepository.findById( id ).orElse(null);

        if (toModify != null) {
            boolean authorNotEmpty = !modified.getAuthor().isEmpty();
            boolean textNotEmpty = !modified.getText().isEmpty();

            if (authorNotEmpty) { toModify.setAuthor(modified.getAuthor()); }
            if (textNotEmpty) { toModify.setText(modified.getText()); }

            quoteRepository.save(toModify);
        } else {
            throw new RecordNotFoundException(NOT_FOUND);
        }
    }

    public Quote delete( long id ) {
        Optional<Quote> toFind = quoteRepository.findById( id );
        if (toFind.isPresent()) {  // check if quote exists
            Quote toDelete = toFind.get();
            quoteRepository.delete( toDelete );
            return toDelete;
        } else {  // quote does not exists
            throw new RecordNotFoundException(NOT_FOUND);
        }
    }
}
