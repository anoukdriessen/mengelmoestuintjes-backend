package nl.mengelmoestuintjes.gardening.controller;

import nl.mengelmoestuintjes.gardening.dto.QuoteRequestDto;
import nl.mengelmoestuintjes.gardening.dto.QuoteResponseDto;
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
public class QuoteController {
    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping
    public QuoteResponseDto newQuote(@Valid @RequestBody QuoteRequestDto toAdd ) {
        Quote quote = quoteService.newQuote( toAdd.toQuote() );
        return QuoteResponseDto.fromQuote(quote);
    }

    @GetMapping
    public List<QuoteResponseDto> getAllQuotes() {
        List<QuoteResponseDto> all = new ArrayList<QuoteResponseDto>();
        Iterable<Quote> quotes = quoteService.getAll();

        for ( Quote q : quotes ) {
            all.add( QuoteResponseDto.fromQuote( q ) );
        }
        return all;
    }

    @GetMapping(value = "/{id}")
    public QuoteResponseDto getById(@PathVariable( "id" ) long id) {
        Quote quote = quoteService.getById( id );
        return QuoteResponseDto.fromQuote(quote);
    }

    @GetMapping(value = "/random")
    public ResponseEntity<Object> getRandomQuote() {
        return ResponseEntity.ok(quoteService.getRandomQuote());
    }

    @PutMapping(value = "/{id}")
    public QuoteResponseDto update( @PathVariable( "id" ) long id, @RequestBody Quote modified ) {
        quoteService.updateQuote( id, modified );
        return QuoteResponseDto.fromQuote( modified );
    }

    @DeleteMapping(value = "/{id}")
    public QuoteResponseDto delete(@PathVariable( "id" ) long id) {
        return QuoteResponseDto.fromQuote( quoteService.delete( id ) );
    }
}
