package com.bakulin.trialtaskquotes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class QuoteNotFoundExeption extends RuntimeException {
    public QuoteNotFoundExeption(String message) {
        super(message);
    }
}
