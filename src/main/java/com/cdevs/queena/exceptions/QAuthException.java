package com.cdevs.queena.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED )
public class QAuthException extends RuntimeException{
    public QAuthException(String message){
        super(message);
    }
}
