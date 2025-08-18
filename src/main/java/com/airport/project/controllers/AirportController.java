package com.airport.project.controllers;

import com.airport.project.controllers.responses.CreateResponse;
import com.airport.project.controllers.responses.MessageResponse;
import com.airport.project.dtos.AirportDTO;
import com.airport.project.controllers.requests.airports.AirportCreateRequest;
import com.airport.project.controllers.requests.airports.AirportUpdateRequest;
import com.airport.project.services.AirportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
public class AirportController {
    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @PostMapping
    ResponseEntity<CreateResponse> createAirport(@RequestBody AirportCreateRequest payload) {
        AirportDTO airportDTO = airportService.createAirport(payload);
        return new ResponseEntity<>(new CreateResponse(airportDTO.getId()), HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<AirportDTO>> getAllAirports() {
        List<AirportDTO> airportDTOEntities = airportService.getAllAirports();
        return new ResponseEntity<>(airportDTOEntities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<AirportDTO> getAirportById(@PathVariable String id) {
        AirportDTO airport = airportService.getAirportById(id);
        return new ResponseEntity<>(airport, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<AirportDTO> updateAirport(@RequestBody AirportUpdateRequest payload, @PathVariable String id) {
        AirportDTO airport = airportService.updateAirport(payload, id);
        return new ResponseEntity<>(airport, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<MessageResponse> deleteAirportById(@PathVariable String id) {
        airportService.deleteAirportById(id);
        return new ResponseEntity<>(new MessageResponse("Airport " + id + " deleted successfully"), HttpStatus.OK);
    }
}
