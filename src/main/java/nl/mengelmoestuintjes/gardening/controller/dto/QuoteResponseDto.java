package nl.mengelmoestuintjes.gardening.controller.dto;

import nl.mengelmoestuintjes.gardening.model.Quote;

public class QuoteResponseDto {

    public long id;
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
