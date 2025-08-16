package com.airport.project.exceptions;

import com.airport.project.controllers.airports.responses.AirportMessageResponse;
import com.airport.project.exceptions.airports.AirportAlreadyExistsException;
import com.airport.project.exceptions.airports.AirportNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(AirportNotFoundException.class)
    public ResponseEntity<AirportMessageResponse> handleAirportNotFoundException(AirportNotFoundException ex) {
        return new ResponseEntity<>(new AirportMessageResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AirportAlreadyExistsException.class)
    public ResponseEntity<AirportMessageResponse> handleAirportAlreadyExistsException(AirportAlreadyExistsException ex) {
        return new ResponseEntity<>(new AirportMessageResponse(ex.getMessage()), HttpStatus.CONFLICT);
    }
}
