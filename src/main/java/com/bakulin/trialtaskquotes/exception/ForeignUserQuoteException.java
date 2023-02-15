package com.bakulin.trialtaskquotes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForeignUserQuoteException extends RuntimeException {
    public ForeignUserQuoteException(String message) {
        super(message);
    }
}
