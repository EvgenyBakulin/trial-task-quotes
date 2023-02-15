package com.bakulin.trialtaskquotes.repository;

import com.bakulin.trialtaskquotes.entity.Quote;
import com.bakulin.trialtaskquotes.entity.Users;
import com.bakulin.trialtaskquotes.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote,Long> {
    Vote findByVoterAndQuote(Users voter, Quote quote);
}
