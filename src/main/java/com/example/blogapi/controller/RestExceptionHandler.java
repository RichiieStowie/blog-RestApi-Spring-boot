package com.example.blogapi.controller;

import com.example.blogapi.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }


    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex){
        ApiError apiError= new ApiError();
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage("Check email address or password");

        return buildResponseEntity(apiError);

    }


    @ExceptionHandler(PostNotFoundException.class)
    protected ResponseEntity<Object> handlePostNotFound(PostNotFoundException ex){
        ApiError apiError= new ApiError();
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage("Empty posts not allowed");

        return buildResponseEntity(apiError);

    }

    @ExceptionHandler(CommentNotFoundException.class)
    protected ResponseEntity<Object> handleCommentNotFound(PostNotFoundException ex){
        ApiError apiError= new ApiError();
        apiError.setStatus(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage("Comment not found");

        return buildResponseEntity(apiError);

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ApiError apiError= new ApiError();
        apiError.setStatus(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage("Resource not found");

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(RegistrationException.class)
    protected  ResponseEntity<Object> handleRegistrationException(RegistrationException ex){
        ApiError apiError= new ApiError();
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        apiError.setDebugMessage("User already exists");

        return buildResponseEntity(apiError);
    }
}
