package com.bakulin.trialtaskquotes.service.implementation;

import com.bakulin.trialtaskquotes.dto.FullQuoteDto;
import com.bakulin.trialtaskquotes.dto.QuoteDto;
import com.bakulin.trialtaskquotes.dto.VoteDto;
import com.bakulin.trialtaskquotes.entity.Quote;
import com.bakulin.trialtaskquotes.entity.Vote;
import com.bakulin.trialtaskquotes.exception.DoubleVotingException;
import com.bakulin.trialtaskquotes.exception.ForeignUserQuoteException;
import com.bakulin.trialtaskquotes.exception.QuoteNotFoundExeption;
import com.bakulin.trialtaskquotes.exception.UserNotFoundException;
import com.bakulin.trialtaskquotes.mapper.FullQuoteDtoMapper;
import com.bakulin.trialtaskquotes.mapper.QuoteDtoMapper;
import com.bakulin.trialtaskquotes.mapper.VoteDtoMapper;
import com.bakulin.trialtaskquotes.repository.QuoteRepository;
import com.bakulin.trialtaskquotes.repository.UserRepository;
import com.bakulin.trialtaskquotes.repository.VoteRepository;
import com.bakulin.trialtaskquotes.service.QuoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuoteServiceImpl implements QuoteService {
    private final QuoteRepository quoteRepository;
    private final QuoteDtoMapper quoteDtoMapper;
    private final FullQuoteDtoMapper fullQuoteDtoMapper;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;
    private final VoteDtoMapper voteDtoMapper;

    public QuoteServiceImpl(QuoteRepository quoteRepository, QuoteDtoMapper quoteDtoMapper,
                            FullQuoteDtoMapper fullQuoteDtoMapper, UserRepository userRepository,
                            VoteRepository voteRepository, VoteDtoMapper voteDtoMapper) {
        this.quoteRepository = quoteRepository;
        this.quoteDtoMapper = quoteDtoMapper;
        this.fullQuoteDtoMapper = fullQuoteDtoMapper;
        this.userRepository = userRepository;
        this.voteDtoMapper = voteDtoMapper;
        this.voteRepository = voteRepository;
    }

    Logger logger = LoggerFactory.getLogger(QuoteServiceImpl.class);

    /**
     * creating quote
     */
    public Quote createQuote(QuoteDto quoteDto) {
        if (userRepository.findById(quoteDto.getPublisher()).isEmpty()) {
            throw new UserNotFoundException("User not found");
        } else {
            Quote quote = quoteDtoMapper.qouteDtoToQuote(quoteDto);
            quote.setDateOfPublication(OffsetDateTime.now());
            quote.setVotes(new ArrayList<>());
            logger.info("createQuote service method in work");
            return quoteRepository.save(quote);
        }
    }

    /**
     * getting quote by id
     */
    public FullQuoteDto getQuote(Long id) {
        logger.info("getQuote service method in work");
        return fullQuoteDtoMapper.quoteToFullQuoteDto(quoteRepository.findById(id).orElseThrow(() -> new QuoteNotFoundExeption("quote not found")));
    }

    /**
     * updating quote by id
     */
    public FullQuoteDto updateQuote(Long id, QuoteDto quoteDto) {
        Quote quoteUpdate = quoteRepository.findById(id).orElseThrow(() -> new QuoteNotFoundExeption("quote not found"));
        if (quoteUpdate.getPublisher().getId().equals(quoteDto.getPublisher())) {
            quoteUpdate.setText(quoteDto.getQuote());
            quoteUpdate.setDateOfPublication(OffsetDateTime.now());
        } else throw new ForeignUserQuoteException("you can not update this quote");
        quoteRepository.save(quoteUpdate);
        logger.info("updateQuote service method in work");
        return fullQuoteDtoMapper.quoteToFullQuoteDto(quoteUpdate);
    }

    /**
     * delete quote by id
     */
    public void deleteQuote(Long id, Long publisherId) {
        Quote quote = quoteRepository.findById(id).orElseThrow(() -> new QuoteNotFoundExeption("quote not found"));
        if (quote.getPublisher().getId().equals(publisherId)) {
            quoteRepository.delete(quote);
        } else {
            throw new ForeignUserQuoteException("You can not delete this quote");
        }
        logger.info("deleteQuote service method in work");
    }

    /**
     * getting first 10 of the best qoutes
     */
    public List<QuoteDto> getFirstTenQuotes() {
        logger.info("geFirstTentQuote service  method in work");
        if (quoteRepository.findAll().size() < 10)
            return quoteRepository.findAll().stream()
                    .sorted((e1, e2) -> getScores(e2).compareTo(getScores(e1)))
                    .map(quoteDtoMapper::qouteToQuoteDto).collect(Collectors.toList());
        else return quoteRepository.findAll().stream()
                .sorted((e1, e2) -> getScores(e2).compareTo(getScores(e1)))
                .map(quoteDtoMapper::qouteToQuoteDto).limit(10).collect(Collectors.toList());
    }

    /**
     * getting last 10 of the best qoutes
     */
    public List<QuoteDto> getLastTenQuotes() {
        logger.info("getLastTentQuote service method in work");
        if (quoteRepository.findAll().size() < 10)
            return quoteRepository.findAll().stream()
                    .sorted((e1, e2) -> getScores(e2).compareTo(getScores(e1)))
                    .map(quoteDtoMapper::qouteToQuoteDto).collect(Collectors.toList());
        else return quoteRepository.findAll().stream()
                .sorted((e1, e2) -> getScores(e2).compareTo(getScores(e1)))
                .map(quoteDtoMapper::qouteToQuoteDto).skip(quoteRepository.findAll().size() - 10).collect(Collectors.toList());
    }

    /**
     * getting random quote
     */
    public FullQuoteDto getRandomQuote() {
        logger.info("getRandom service method in work");
        return fullQuoteDtoMapper.quoteToFullQuoteDto(quoteRepository.findRandomQuote());
    }

    /**
     * voting for quote
     */
    public Vote createVote(VoteDto voteDto, boolean voting) {
        if (voteRepository.findByVoterAndQuote(userRepository.findById(voteDto.getVoter()).orElseThrow(() -> new UserNotFoundException("User not found")),
                quoteRepository.findById(voteDto.getQuote()).orElseThrow(() -> new QuoteNotFoundExeption("quote not found"))) != null) {
            throw new DoubleVotingException("Yet voited");
        } else {
            Vote vote = voteDtoMapper.voteDtoToVote(voteDto);
            Quote quote = quoteRepository.findById(vote.getQuote().getId()).orElseThrow(() -> new QuoteNotFoundExeption("Quote not found"));
            List<Vote> list = quote.getVotes();
            if (voting) {
                vote.setVote(1);

            } else {
                vote.setVote(-1);
            }
            list.add(vote);
            quote.setVotes(list);
            quoteRepository.save(quote);
            logger.info("createVote service method in work");
            return voteRepository.save(vote);
        }
    }

    /**
     * method for counting scores
     */
    private Integer getScores(Quote quote) {
        if (quote.getVotes().isEmpty()) {
            return 0;
        } else {
            return quote.getVotes().stream().mapToInt(Vote::getVote).sum();
        }
    }

}
