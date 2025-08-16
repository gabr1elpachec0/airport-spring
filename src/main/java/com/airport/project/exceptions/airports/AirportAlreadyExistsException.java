package com.airport.project.exceptions.airports;

public class AirportAlreadyExistsException extends RuntimeException {
    public AirportAlreadyExistsException(String message) {
        super(message);
    }
}
