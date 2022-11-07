package com.example.meetup.exception;

public class InvalidDateFormatException extends RuntimeException{
    public InvalidDateFormatException(String message) {
        super(message);
    }
}
