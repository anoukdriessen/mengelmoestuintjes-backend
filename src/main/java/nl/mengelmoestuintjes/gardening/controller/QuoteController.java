package nl.mengelmoestuintjes.gardening.controller;

import nl.mengelmoestuintjes.gardening.controller.exceptions.BadRequestException;
import nl.mengelmoestuintjes.gardening.controller.exceptions.RecordNotFoundException;
import nl.mengelmoestuintjes.gardening.model.Quote;
import nl.mengelmoestuintjes.gardening.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/quotes")
@CrossOrigin
public class QuoteController {
    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping
    public Quote newQuote(
            @RequestBody Quote toAdd
    ) {
        try {
            return quoteService.newQuote(toAdd);
        } catch (Exception e) {
            throw new BadRequestException("cannot add quote");
        }
    }

    @GetMapping
    public Iterable<Quote> getAllQuotes() {
        return quoteService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Quote getById(
            @PathVariable( "id" ) long id
    ) {
        try {
            return quoteService.getById(id);
        } catch (Exception e) {
            throw new RecordNotFoundException("quote not found");
        }
    }

    @GetMapping(value = "/random")
    public ResponseEntity<Object> getRandomQuote() {
        return ResponseEntity.ok(quoteService.getRandomQuote());
    }

    @PutMapping(value = "/{id}")
    public String update(
            @PathVariable( "id" ) long id,
            @RequestBody Quote modified
    ) {
        try {
            return quoteService.updateQuote(id, modified);
        } catch (Exception e) {
            throw new BadRequestException("cannot update quote");
        }
    }

    @DeleteMapping(value = "/{id}")
    public String delete(
            @PathVariable( "id" ) long id
    ) {
        return quoteService.delete( id );
    }
}
