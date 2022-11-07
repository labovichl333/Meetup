package com.example.meetup.exception;

public class MeetupNotFoundException extends RuntimeException {

    public MeetupNotFoundException(String message) {
        super(message);
    }
}
