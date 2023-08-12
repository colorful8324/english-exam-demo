package com.englishexamweb.demo.exceptions;

public class ExamNotFoundException extends RuntimeException {
    public ExamNotFoundException(String message) {
        super(message);
    }

    public ExamNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExamNotFoundException(Throwable cause) {
        super(cause);
    }
}
