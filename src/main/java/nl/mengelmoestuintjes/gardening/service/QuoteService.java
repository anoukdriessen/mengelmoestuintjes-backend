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

    @Autowired
    private QuoteRepository quoteRepository;

    // Create
    public int newQuote(Quote toAdd) {
        Quote quote = new Quote(toAdd.getAuthor(), toAdd.getText());
        Quote newQuote = quoteRepository.save(quote);
        return newQuote.getId();
    }

    // Read
    public Iterable<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

    public Quote getQuoteById(int id) {
        Optional<Quote> toFind = quoteRepository.findById(id);

        if (toFind.isPresent()) {   // check if quote exists
            return toFind.get();
        } else {                    // post does not exists
            throw new RecordNotFoundException(NOT_FOUND);
        }
    }

    public Quote getRandomQuote() {
        long bound = quoteRepository.count();
        Random r = new Random();
        int id = r.nextInt((int) bound - 1);
        return getQuoteById(id);
    }

    // Update
    public void updateQuote(int id, Quote modified){
        Quote toModify = quoteRepository.findById(id).orElse(null);

        if (toModify != null) {
            boolean authorNotEmpty = !modified.getAuthor().isEmpty() && modified.getAuthor() != null;
            boolean textNotEmpty = !modified.getText().isEmpty() && modified.getText() != null;

            if (authorNotEmpty) {
                toModify.setAuthor(modified.getAuthor());
            }
            if (textNotEmpty) {
                toModify.setText(modified.getText());
            }
            quoteRepository.save(toModify);
        }
    }

    // Delete
    public void deleteQuoteById(int id) {
        Optional<Quote> toFind = quoteRepository.findById(id);

        if (toFind.isPresent()) {   // check if quote exists
            quoteRepository.deleteById(id);
        } else {                    // post does not exists
            throw new RecordNotFoundException(NOT_FOUND);
        }
    }
}
