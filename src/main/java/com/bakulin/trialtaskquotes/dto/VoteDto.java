package com.bakulin.trialtaskquotes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteDto {
    private Long voter;
    private Long quote;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoteDto voteDto)) return false;
        return Objects.equals(getVoter(), voteDto.getVoter()) && Objects.equals(getQuote(), voteDto.getQuote());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVoter(), getQuote());
    }
}
