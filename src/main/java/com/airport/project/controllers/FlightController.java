package com.airport.project.controllers;

import com.airport.project.controllers.requests.flights.CreateFlightRequest;
import com.airport.project.controllers.requests.flights.FlightUpdateRequest;
import com.airport.project.controllers.responses.CreateResponse;
import com.airport.project.controllers.responses.MessageResponse;
import com.airport.project.dtos.FlightDTO;
import com.airport.project.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping
    ResponseEntity<List<FlightDTO>> getAllFlights() {
        List<FlightDTO> flights = flightService.getAllFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping("/{flightId}")
    ResponseEntity<FlightDTO> getFlightById(@PathVariable UUID flightId) {
        FlightDTO flight = flightService.getFlightById(flightId);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @PutMapping("/{flightId}")
    ResponseEntity<FlightDTO> updateFlight(@PathVariable UUID flightId, @RequestBody FlightUpdateRequest payload) {
        FlightDTO flight = flightService.updateFlight(flightId, payload);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @DeleteMapping("/{flightId}")
    ResponseEntity<MessageResponse> deleteFlight(@PathVariable UUID flightId) {
        flightService.deleteFlight(flightId);
        return new ResponseEntity<>(new MessageResponse("Flight " + flightId + " deleted successfully."), HttpStatus.OK);
    }
}
