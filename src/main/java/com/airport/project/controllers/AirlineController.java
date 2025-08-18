package com.airport.project.controllers;

import com.airport.project.controllers.requests.airlines.AirlineCreateRequest;
import com.airport.project.dtos.AirlineDTO;
import com.airport.project.services.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/airlines")
public class AirlineController {
    @Autowired
    private AirlineService airlineService;

    @PostMapping
    ResponseEntity<AirlineDTO> createAirline(@RequestBody AirlineCreateRequest payload) {
        AirlineDTO airline = airlineService.createAirline(payload);
        return new ResponseEntity<>(airline, HttpStatus.CREATED);
    }
}
