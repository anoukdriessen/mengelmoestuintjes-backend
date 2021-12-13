package nl.mengelmoestuintjes.gardening.controller;

import nl.mengelmoestuintjes.gardening.model.Quote;
import nl.mengelmoestuintjes.gardening.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/quotes")
@CrossOrigin
public class QuoteController {

    private QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    // Create
    @PostMapping(value = "")
    public ResponseEntity<Object> newQuote(Quote toAdd) {
        int newId = quoteService.newQuote(toAdd);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newId).toUri();
        return ResponseEntity.created(location).build();
    }

    // Read
    @GetMapping(value = "")
    public ResponseEntity<Object> getAllQuotes() {
        return ResponseEntity.ok(quoteService.getAllQuotes());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getQuoteById(@PathVariable int id) {
        return ResponseEntity.ok(quoteService.getQuoteById(id));
    }

    @GetMapping(value = "/random")
    public ResponseEntity<Object> getRandomQuote() {
        return ResponseEntity.ok(quoteService.getRandomQuote());
    }

    // Update
    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateQuote(@PathVariable("id") int id, @RequestBody Quote modifiedQuote) {
        quoteService.updateQuote(id, modifiedQuote);
        return ResponseEntity.noContent().build();
    }

    // Delete
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteQuoteById(@PathVariable("id") int id) {
        quoteService.deleteQuoteById(id);
        return ResponseEntity.noContent().build();
    }
}
