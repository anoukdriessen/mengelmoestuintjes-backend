package nl.mengelmoestuintjes.gardening.controller.dto;

import nl.mengelmoestuintjes.gardening.model.Quote;

import javax.validation.constraints.NotBlank;

public class QuoteRequestDto {
    @NotBlank
    public String author;
    @NotBlank
    public String text;

    public Quote toQuote() {
        return new Quote(
                author,
                text
        );
    }
}
