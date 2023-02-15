package com.bakulin.trialtaskquotes.dto;

import com.bakulin.trialtaskquotes.entity.Vote;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullQuoteDto {
    private String quote;
    private Long publisher;
    private OffsetDateTime dateOfPublication;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FullQuoteDto that)) return false;
        return Objects.equals(getQuote(), that.getQuote()) && Objects.equals(getPublisher(), that.getPublisher()) && Objects.equals(getDateOfPublication(), that.getDateOfPublication());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuote(), getPublisher(), getDateOfPublication());
    }
}
