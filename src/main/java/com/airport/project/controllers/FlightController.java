package com.airport.project.controllers;

import com.airport.project.controllers.requests.flights.CreateFlightRequest;
import com.airport.project.controllers.responses.CreateResponse;
import com.airport.project.dtos.FlightDTO;
import com.airport.project.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @PostMapping
    ResponseEntity<CreateResponse> createFlight(@RequestBody CreateFlightRequest payload) {
        CreateResponse flightId = flightService.createFlight(payload);
        return new ResponseEntity<>(flightId, HttpStatus.CREATED);
    }
}
