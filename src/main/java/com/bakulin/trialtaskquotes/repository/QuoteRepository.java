package com.bakulin.trialtaskquotes.repository;

import com.bakulin.trialtaskquotes.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface QuoteRepository extends JpaRepository<Quote, Long> {
    @Query(value = "SELECT * FROM QUOTE ORDER BY RAND() LIMIT 1",nativeQuery = true)
    Quote findRandomQuote();
}
