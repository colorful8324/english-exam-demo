package com.englishexamweb.demo.exceptions;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler({QuestionNotFoundException.class, TopicNotFoundException.class, ExamNotFoundException.class, CSVException.class})
    public ResponseEntity<ErrorResponse> handleEntityException(Exception exc) {
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());
        exc.printStackTrace();

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnwantedException(Exception exc) {
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());
        exc.printStackTrace();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
