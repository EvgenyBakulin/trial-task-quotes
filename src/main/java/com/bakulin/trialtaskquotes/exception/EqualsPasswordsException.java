package com.bakulin.trialtaskquotes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class EqualsPasswordsException extends RuntimeException {
    public EqualsPasswordsException(String message) {
        super(message);
    }
}
