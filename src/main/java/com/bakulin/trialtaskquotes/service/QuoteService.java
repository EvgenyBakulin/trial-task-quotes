package com.bakulin.trialtaskquotes.service;

import com.bakulin.trialtaskquotes.dto.FullQuoteDto;
import com.bakulin.trialtaskquotes.dto.QuoteDto;
import com.bakulin.trialtaskquotes.dto.VoteDto;
import com.bakulin.trialtaskquotes.entity.Quote;
import com.bakulin.trialtaskquotes.entity.Vote;

import java.util.List;

public interface QuoteService {
    Quote createQuote(QuoteDto quoteDto);

    FullQuoteDto getQuote(Long id);

    FullQuoteDto updateQuote(Long id, QuoteDto quoteDto);

    void deleteQuote(Long id, Long publisherId);

    List<QuoteDto> getFirstTenQuotes();

    List<QuoteDto> getLastTenQuotes();

    FullQuoteDto getRandomQuote();

    Vote createVote(VoteDto voteDto, boolean voting);

}
