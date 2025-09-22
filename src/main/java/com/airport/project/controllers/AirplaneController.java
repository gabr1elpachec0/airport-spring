package com.airport.project.controllers;

import com.airport.project.controllers.requests.airplanes.AirplaneCreateRequest;
import com.airport.project.controllers.requests.airplanes.AirplaneUpdateRequest;
import com.airport.project.controllers.responses.CreateResponse;
import com.airport.project.controllers.responses.MessageResponse;
import com.airport.project.dtos.AirplaneDTO;
import com.airport.project.services.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping
    ResponseEntity<List<AirplaneDTO>> getAllAirplanes() {
        List<AirplaneDTO> airplanes = airplaneService.getAllAirplanes();
        return new ResponseEntity<>(airplanes, HttpStatus.OK);
    }

    @GetMapping("/by-airline/{airlineId}")
    ResponseEntity<List<AirplaneDTO>> getAllAirplanesByAirline(@PathVariable UUID airlineId) {
        List<AirplaneDTO> airplanes = airplaneService.getAllAirplanesByAirlineId(airlineId);
        return new ResponseEntity<>(airplanes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<AirplaneDTO> getAirplaneById(@PathVariable UUID id) {
        AirplaneDTO airplane = airplaneService.getAirplaneById(id);
        return new ResponseEntity<>(airplane, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<AirplaneDTO> updateAirplane(@PathVariable UUID id, @RequestBody AirplaneUpdateRequest payload) {
        AirplaneDTO airplane = airplaneService.updateAirplane(id, payload);
        return new ResponseEntity<>(airplane, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<MessageResponse> deleteAirplane(@PathVariable UUID id) {
        airplaneService.deleteAirplane(id);
        return new ResponseEntity<>(new MessageResponse("Airplane " + id + " deleted successfully."), HttpStatus.OK);
    }
}
