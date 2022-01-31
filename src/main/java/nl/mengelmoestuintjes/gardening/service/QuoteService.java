package nl.mengelmoestuintjes.gardening.service;

import nl.mengelmoestuintjes.gardening.controller.exceptions.BadRequestException;
import nl.mengelmoestuintjes.gardening.controller.exceptions.RecordNotFoundException;
import nl.mengelmoestuintjes.gardening.model.Quote;
import nl.mengelmoestuintjes.gardening.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class QuoteService {
    private final QuoteRepository quoteRepository;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public Quote newQuote(Quote toAdd) {
        if (toAdd.getText() == null || toAdd.getText().isEmpty() || toAdd.getText().isBlank()) {
            throw new BadRequestException("Quote cannot be empty");
        } else if (toAdd.getAuthor() == null || toAdd.getAuthor().isEmpty() || toAdd.getAuthor().isBlank()) {
            throw new BadRequestException("Author cannot be empty");
        } else {
            return quoteRepository.save( toAdd );
        }
    }

    public Iterable<Quote> getAll() {
//        return quoteRepository.findAll(Pageable.ofSize(20));
        return quoteRepository.findAll();
    }

    public Quote getById( long id ) {
        Optional<Quote> toFind = quoteRepository.findByIdOrderById( id );
        if (toFind.isPresent()) {  // check if quote exists
            return toFind.get();
        } else { // quote does not exists
            throw new RecordNotFoundException("cannot find quote");
        }
    }

    public Quote getRandomQuote() {
        long bound = quoteRepository.count();
        Random r = new Random();
        int id = r.nextInt( ( int ) bound - 1);
        return getById( id );
    }

    public String updateQuote( long id, Quote modified){
        String updated = "";
        Quote toModify = getById( id );
        try {
            boolean authorNotEmpty = !modified.getAuthor().isEmpty();
            boolean textNotEmpty = !modified.getText().isEmpty();

            if (authorNotEmpty) {
                toModify.setAuthor(modified.getAuthor());
                updated += "author ";
            }
            if (textNotEmpty) {
                toModify.setText(modified.getText());
                updated += "text ";
            }
            quoteRepository.save(toModify);
            if (updated.isBlank()) {
                updated = "NOTHING ";
            }
            return updated + " has been updated";
        } catch (Exception e) {
            throw new BadRequestException("cannot update quote");
        }
    }

    public String delete( long id ) {
        if (quoteRepository.existsById(id)) {
            Quote quote = getById(id);
            quoteRepository.delete( quote );
            return quote.getText() + " is deleted";
        }
        throw new BadRequestException("cannot delete quote");
    }
}
