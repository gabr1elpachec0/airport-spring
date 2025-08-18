package com.airport.project.controllers;

import com.airport.project.controllers.requests.airlines.AirlineCreateRequest;
import com.airport.project.controllers.responses.CreateResponse;
import com.airport.project.dtos.AirlineDTO;
import com.airport.project.services.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/airlines")
public class AirlineController {
    @Autowired
    private AirlineService airlineService;

    @PostMapping
    ResponseEntity<CreateResponse> createAirline(@RequestBody AirlineCreateRequest payload) {
        CreateResponse airlineId = airlineService.createAirline(payload);
        return new ResponseEntity<>(airlineId, HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<AirlineDTO>> getAllAirlines() {
        List<AirlineDTO> airlines = airlineService.getAllAirlines();
        return new ResponseEntity<>(airlines, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<AirlineDTO> getAirlineById(@PathVariable UUID id) {
        AirlineDTO airline = airlineService.getAirlineById(id);
        return new ResponseEntity<>(airline, HttpStatus.OK);
    }
}