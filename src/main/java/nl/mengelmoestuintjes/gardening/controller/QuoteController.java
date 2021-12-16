package nl.mengelmoestuintjes.gardening.controller;

import nl.mengelmoestuintjes.gardening.controller.dto.quote.QuoteRequestDto;
import nl.mengelmoestuintjes.gardening.controller.dto.quote.QuoteResponseDto;
import nl.mengelmoestuintjes.gardening.model.Quote;
import nl.mengelmoestuintjes.gardening.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/quotes")
@CrossOrigin
public class QuoteController {
    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    // Create
    @PostMapping
    public QuoteResponseDto newQuote(@Valid @RequestBody QuoteRequestDto toAdd ) {
        Quote quote = quoteService.newQuote( toAdd.toQuote() );
        return QuoteResponseDto.fromQuote(quote);
    }

    // Read
    @GetMapping
    public List<QuoteResponseDto> getAllQuotes() {
        List<QuoteResponseDto> all = new ArrayList<QuoteResponseDto>();
        Iterable<Quote> quotes = quoteService.getAllQuotes();

        for ( Quote q : quotes ) {
            all.add( QuoteResponseDto.fromQuote( q ) );
        }
        return all;
    }

    @GetMapping(value = "/{id}")
    public QuoteResponseDto getQuoteById(@PathVariable( "id" ) int id) {
        Quote quote = quoteService.getQuoteById(id);
        return QuoteResponseDto.fromQuote(quote);
    }

    @GetMapping(value = "/random")
    public ResponseEntity<Object> getRandomQuote() {
        return ResponseEntity.ok(quoteService.getRandomQuote());
    }

    // Update
    @PutMapping(value = "/{id}")
    public QuoteResponseDto updateQuote( @PathVariable( "id" ) int id, @RequestBody Quote modifiedQuote ) {
        quoteService.updateQuote(id, modifiedQuote);
        return QuoteResponseDto.fromQuote(modifiedQuote);
    }

    // Delete
    @DeleteMapping(value = "/{id}")
    public void deleteQuote(@PathVariable( "id" ) int id) {
        quoteService.deleteQuoteById(id);
    }
}
