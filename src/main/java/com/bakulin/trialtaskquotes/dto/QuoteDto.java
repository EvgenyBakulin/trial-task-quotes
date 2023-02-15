package com.bakulin.trialtaskquotes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuoteDto {
    private String quote;
    private Long publisher;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuoteDto quoteDto)) return false;
        return Objects.equals(getQuote(), quoteDto.getQuote()) && Objects.equals(getPublisher(), quoteDto.getPublisher());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuote(), getPublisher());
    }

    @Override
    public String toString() {
        return "QuoteDto{" +
                "quote='" + quote + '\'' +
                ", publisher=" + publisher +
                '}';
    }
}
