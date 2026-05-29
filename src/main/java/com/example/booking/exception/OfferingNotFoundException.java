package com.example.booking.exception;

public class OfferingNotFoundException
        extends RuntimeException {

    public OfferingNotFoundException(
            String message
    ) {
        super(message);
    }
}