package com.airport.project.controllers;

import com.airport.project.controllers.requests.airplanes.AirplaneCreateRequest;
import com.airport.project.controllers.responses.CreateResponse;
import com.airport.project.services.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/airplanes")
public class AirplaneController {
    @Autowired
    private AirplaneService airplaneService;

    @PostMapping
    ResponseEntity<CreateResponse> createAirplane(@RequestBody AirplaneCreateRequest payload) {
        CreateResponse airplaneId = airplaneService.createAirplane(payload);
        return new ResponseEntity<>(airplaneId, HttpStatus.CREATED);
    }
}
