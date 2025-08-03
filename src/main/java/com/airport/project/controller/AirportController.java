package com.airport.project.controller;

import com.airport.project.dto.AirportDTO;
import com.airport.project.service.AirportService;
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
    ResponseEntity<AirportDTO> createAirport(@RequestBody AirportDTO body) {
        AirportDTO airport = airportService.createAirport(body);
        return new ResponseEntity<>(airport, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<AirportDTO> getAirportById(@PathVariable String id) {
        AirportDTO airport = airportService.getAirportById(id);
        return new ResponseEntity<>(airport, HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<List<AirportDTO>> getAllAirports() {
        List<AirportDTO> airports = airportService.getAllAirports();
        return new ResponseEntity<>(airports, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteAirportById(@PathVariable String id) {
        airportService.deleteAirportById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
