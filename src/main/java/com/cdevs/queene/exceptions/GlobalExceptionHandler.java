package com.cdevs.queene.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cdevs.queene.responseentity.APIResponse;
import com.cdevs.queene.responseentity.BasicResponse;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.persistence.EntityExistsException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse> resourceNotFound(ResourceNotFoundException e){
        BasicResponse response  = new BasicResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<APIResponse> invalidCredentials(BadCredentialsException e){
        BasicResponse response  = new BasicResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<APIResponse> entityExists(EntityExistsException e){
        BasicResponse response  = new BasicResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(NoTokenProvidedException.class)
    public ResponseEntity<APIResponse> noTokenProvided(NoTokenProvidedException e){
        BasicResponse response  = new BasicResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<APIResponse> expiredJWT(ExpiredJwtException e){
        BasicResponse response  = new BasicResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
