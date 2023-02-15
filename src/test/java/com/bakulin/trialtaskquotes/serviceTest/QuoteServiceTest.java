package com.bakulin.trialtaskquotes.serviceTest;

import com.bakulin.trialtaskquotes.dto.FullQuoteDto;
import com.bakulin.trialtaskquotes.dto.QuoteDto;
import com.bakulin.trialtaskquotes.dto.VoteDto;
import com.bakulin.trialtaskquotes.entity.Quote;
import com.bakulin.trialtaskquotes.entity.Users;
import com.bakulin.trialtaskquotes.entity.Vote;
import com.bakulin.trialtaskquotes.mapper.FullQuoteDtoMapper;
import com.bakulin.trialtaskquotes.mapper.QuoteDtoMapper;
import com.bakulin.trialtaskquotes.mapper.VoteDtoMapper;
import com.bakulin.trialtaskquotes.repository.QuoteRepository;
import com.bakulin.trialtaskquotes.repository.UserRepository;
import com.bakulin.trialtaskquotes.repository.VoteRepository;
import com.bakulin.trialtaskquotes.service.implementation.QuoteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.data.projection.EntityProjection.ProjectionType.DTO;

@ExtendWith(MockitoExtension.class)
public class QuoteServiceTest {
    @Mock
    private QuoteRepository quoteRepository;
    @Mock
    private QuoteDtoMapper quoteDtoMapper;
    @Mock
    private FullQuoteDtoMapper fullQuoteDtoMapper;
    @Mock
    private UserRepository userRepository;
    @Mock
    private VoteDtoMapper voteDtoMapper;
    @Mock
    private VoteRepository voteRepository;
    @InjectMocks
    private QuoteServiceImpl quoteService;


    Users USER = new Users(1L,"name","o@a.com","111",OffsetDateTime.now());
    Users USER1 = new Users(2L,"name1","w@a.com","123",OffsetDateTime.now());
    List<Vote> LIST = new ArrayList<>();
    List<Vote>LIST1 = new ArrayList<>();
    Quote QUOTE = new Quote(1L,"text",OffsetDateTime.now(),USER,LIST);
    Quote QUOTE1 = new Quote();

    Quote QUOTE2 = new Quote(2L,"text1",OffsetDateTime.now(),USER,LIST1);
    QuoteDto DTO= new QuoteDto("text",1L);
    QuoteDto DTO2= new QuoteDto("text1",2L);
    List <Quote> LIST_OF_QUOTES= new ArrayList<>();
    List<QuoteDto> LIST_OF_DTO = new ArrayList<>();
    FullQuoteDto FULL_DTO = new FullQuoteDto("text",1L,OffsetDateTime.now());
    Vote VOTE = new Vote(1L,USER,QUOTE2,1);
    Vote VOTE1 = new Vote(2L,USER1,QUOTE,-1);
    @Test
    void createQuoteTest() {
        QUOTE1.setId(1l);
        QUOTE1.setText("text");
        QUOTE1.setPublisher(USER);
        QUOTE1.setDateOfPublication(OffsetDateTime.now());
        QUOTE1.setVotes(new ArrayList<Vote>());
        when(quoteDtoMapper.qouteDtoToQuote(DTO)).thenReturn(QUOTE);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(USER));
        when(quoteRepository.save(any(Quote.class))).thenReturn(QUOTE1);
        Quote expectedQuote = quoteService.createQuote(DTO);
        assertEquals(expectedQuote,QUOTE1);
    }
    @Test
    void getQuoteTest() {
        when(quoteRepository.findById(anyLong())).thenReturn(Optional.of(QUOTE));
        when(fullQuoteDtoMapper.quoteToFullQuoteDto(QUOTE)).thenReturn(FULL_DTO);
        FullQuoteDto expectedDto = quoteService.getQuote(1L);
        assertEquals(expectedDto, FULL_DTO);
    }

    @Test
    void getRandomQuoteTest() {
        when(quoteRepository.findRandomQuote()).thenReturn(QUOTE);
        when(fullQuoteDtoMapper.quoteToFullQuoteDto(QUOTE)).thenReturn(FULL_DTO);
        FullQuoteDto expectedDto = quoteService.getRandomQuote();
        assertEquals(expectedDto, FULL_DTO);
    }

    @Test
    void updateQuoteTest() {
        DTO.setQuote("text1");
        QUOTE1.setId(1l);
        QUOTE1.setText("text1");
        QUOTE1.setPublisher(USER);
        QUOTE1.setDateOfPublication(OffsetDateTime.now());
        QUOTE1.setVotes(LIST);
        FULL_DTO.setQuote("text1");
        when(quoteRepository.findById(anyLong())).thenReturn(Optional.of(QUOTE));
        when(quoteRepository.save(any(Quote.class))).thenReturn(QUOTE1);
        when(fullQuoteDtoMapper.quoteToFullQuoteDto(any(Quote.class))).thenReturn(FULL_DTO);
        FullQuoteDto expectedDto = quoteService.updateQuote(1L,DTO);
        assertEquals(expectedDto, FULL_DTO);
    }
    @Test
    void deleteQuoteTest() {
        when(quoteRepository.findById(anyLong())).thenReturn(Optional.of(QUOTE));
        doNothing().when(quoteRepository).delete(any(Quote.class));
        quoteService.deleteQuote(1L,1L);
        verify(quoteRepository, times(1)).delete(QUOTE);
    }

    @Test
    void getFirstTenQuotesTest(){
        LIST.add(VOTE1);
        LIST1.add(VOTE);
        LIST_OF_QUOTES.add(QUOTE);
        LIST_OF_QUOTES.add(QUOTE2);
        LIST_OF_DTO.add(DTO2);
        LIST_OF_DTO.add(DTO);
        QUOTE.setVotes(LIST);
        QUOTE2.setVotes(LIST1);
        when(quoteDtoMapper.qouteToQuoteDto(QUOTE)).thenReturn(DTO);
        when(quoteDtoMapper.qouteToQuoteDto(QUOTE2)).thenReturn(DTO2);
        when(quoteRepository.findAll()).thenReturn(LIST_OF_QUOTES);
        List<QuoteDto> expectedList = quoteService.getFirstTenQuotes();
        assertEquals(expectedList,LIST_OF_DTO);
    }

    @Test
    void getLastTenQuotesTest(){
        LIST.add(VOTE1);
        LIST1.add(VOTE);
        LIST_OF_QUOTES.add(QUOTE);
        LIST_OF_QUOTES.add(QUOTE2);
        LIST_OF_DTO.add(DTO2);
        LIST_OF_DTO.add(DTO);
        QUOTE.setVotes(LIST);
        QUOTE2.setVotes(LIST1);
        when(quoteDtoMapper.qouteToQuoteDto(QUOTE)).thenReturn(DTO);
        when(quoteDtoMapper.qouteToQuoteDto(QUOTE2)).thenReturn(DTO2);
        when(quoteRepository.findAll()).thenReturn(LIST_OF_QUOTES);
        List<QuoteDto> expectedList = quoteService.getFirstTenQuotes();
        assertEquals(expectedList,LIST_OF_DTO);
    }
    @Test
    void createVoteTest() {
        VoteDto voteDto = new VoteDto(1L,1L);
        Vote VOTE = new Vote(1L,USER,QUOTE,1);
        when(voteDtoMapper.voteDtoToVote(voteDto)).thenReturn(VOTE);
        when(voteRepository.save(any(Vote.class))).thenReturn(VOTE);
        when(userRepository.findById(any())).thenReturn(Optional.of(USER));
        when(quoteRepository.findById(any())).thenReturn(Optional.of(QUOTE));
        when(voteRepository.findByVoterAndQuote(any(Users.class),any(Quote.class))).thenReturn(null);
        Vote expectedVote = quoteService.createVote(voteDto,true);
        assertEquals(expectedVote,VOTE);

    }
}
