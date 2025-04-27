package com.example.patternforge.exception;

public class PatternProcessingException extends RuntimeException {
    public PatternProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
