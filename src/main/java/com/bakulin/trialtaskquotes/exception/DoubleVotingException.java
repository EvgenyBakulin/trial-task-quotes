package com.bakulin.trialtaskquotes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class DoubleVotingException extends RuntimeException{
    public DoubleVotingException(String s) {
        super(s);
    }
}
