package com.bakulin.trialtaskquotes.controller;

import com.bakulin.trialtaskquotes.dto.FullQuoteDto;
import com.bakulin.trialtaskquotes.dto.QuoteDto;
import com.bakulin.trialtaskquotes.dto.VoteDto;
import com.bakulin.trialtaskquotes.entity.Quote;
import com.bakulin.trialtaskquotes.entity.Vote;
import com.bakulin.trialtaskquotes.service.QuoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quote")
public class QuoteController {
    private final QuoteService quoteService;


    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    Logger logger = LoggerFactory.getLogger(QuoteController.class);

    /**
     * creating quote
     */
    @PostMapping("/create")
    @Operation(summary = "creating of quote", responses =
            {@ApiResponse(responseCode = "200", description = "Successful"), @ApiResponse(responseCode = "404", description = "Not found")})
    public ResponseEntity<Quote> createQuote(QuoteDto quoteDto) {
        logger.info("createQuote method in work");
        return ResponseEntity.ok(quoteService.createQuote(quoteDto));
    }

    /**
     * getting quote by id
     */
    @GetMapping("/get{id}")
    @Operation(summary = "getting quote by id", responses =
            {@ApiResponse(responseCode = "200", description = "Successful"), @ApiResponse(responseCode = "404", description = "Not found")})
    public ResponseEntity<FullQuoteDto> getQuote(@PathVariable Long id) {
        logger.info("getQuote method in work");
        return ResponseEntity.ok(quoteService.getQuote(id));
    }

    /**
     * updating quote by id
     */
    @PatchMapping("/patch{id}")
    @Operation(summary = "updating quote by id", responses =
            {@ApiResponse(responseCode = "200", description = "Successful"), @ApiResponse(responseCode = "403", description = "Forbidden"), @ApiResponse(responseCode = "404", description = "Not found")})
    public ResponseEntity<FullQuoteDto> updateQuote(@PathVariable Long id, @RequestBody QuoteDto quoteDto) {
        logger.info("updateQuote method in work");
        return ResponseEntity.ok(quoteService.updateQuote(id, quoteDto));
    }

    /**
     * delete quote by id
     */
    @DeleteMapping("/delete{id}")
    @Operation(summary = "deleting quote by id", responses =
            {@ApiResponse(responseCode = "200", description = "Successful"), @ApiResponse(responseCode = "403", description = "Forbidden"), @ApiResponse(responseCode = "404", description = "Not found")})
    public ResponseEntity deleteQuote(@PathVariable Long id, @RequestParam Long publisherId) {
        quoteService.deleteQuote(id, publisherId);
        logger.info("deleteQuote method in work");
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    /**
     * getting first 10 of the best qoutes
     */
    @GetMapping("/first10")
    @Operation(summary = "getting first 10 of the best qoutes", responses =
            {@ApiResponse(responseCode = "200", description = "Successful"), @ApiResponse(responseCode = "404", description = "Not found")})
    public ResponseEntity<List<QuoteDto>> getFirstTenQuotes() {
        logger.info("geFirstTentQuote method in work");
        return ResponseEntity.ok(quoteService.getFirstTenQuotes());
    }

    /**
     * getting last 10 of the best qoutes
     */
    @GetMapping("/last10")
    @Operation(summary = "getting last 10 of the best qoutes", responses =
            {@ApiResponse(responseCode = "200", description = "Successful"), @ApiResponse(responseCode = "404", description = "Not found")})
    public ResponseEntity<List<QuoteDto>> getLastTenQuotes() {
        logger.info("getLastTentQuote method in work");
        return ResponseEntity.ok(quoteService.getLastTenQuotes());
    }

    /**
     * getting random quote
     */
    @GetMapping("/get")
    @Operation(summary = "getting random quote", responses =
            {@ApiResponse(responseCode = "200", description = "Successful"), @ApiResponse(responseCode = "404", description = "Not found")})
    public ResponseEntity<FullQuoteDto> getRandomQuote() {
        logger.info("getRandom method in work");
        return ResponseEntity.ok(quoteService.getRandomQuote());
    }

    /**
     * voting for quote
     */
    @PostMapping("/vote")
    @Operation(summary = "voting for quote", responses =
            {@ApiResponse(responseCode = "200", description = "Successful"), @ApiResponse(responseCode = "403", description = "Forbidden"), @ApiResponse(responseCode = "404", description = "Not found")})
    public ResponseEntity<Vote> createVote(VoteDto voteDto, @RequestParam boolean voting) {
        logger.info("createVote method in work");
        return ResponseEntity.ok(quoteService.createVote(voteDto, voting));
    }
}
