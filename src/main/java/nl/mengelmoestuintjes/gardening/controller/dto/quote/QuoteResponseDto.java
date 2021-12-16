package nl.mengelmoestuintjes.gardening.controller.dto.quote;

import nl.mengelmoestuintjes.gardening.model.Quote;

public class QuoteResponseDto {

    public int id;
    public String author;
    public String text;

    public static QuoteResponseDto fromQuote(Quote quote) {
        QuoteResponseDto dto = new QuoteResponseDto();

        dto.id = quote.getId();
        dto.author = quote.getAuthor();
        dto.text = quote.getText();

        return dto;
    }
}
