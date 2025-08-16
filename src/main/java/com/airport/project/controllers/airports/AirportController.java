package com.airport.project.controllers.airports;

import com.airport.project.controllers.airports.responses.AirportMessageResponse;
import com.airport.project.dtos.airports.AirportDTO;
import com.airport.project.controllers.airports.requests.AirportCreateRequest;
import com.airport.project.controllers.airports.responses.AirportCreateResponse;
import com.airport.project.controllers.airports.requests.AirportUpdateRequest;
import com.airport.project.services.airports.AirportService;
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
    ResponseEntity<AirportCreateResponse> createAirport(@RequestBody AirportCreateRequest payload) {
        AirportDTO airportDTO = airportService.createAirport(payload);
        return new ResponseEntity<>(new AirportCreateResponse(airportDTO.getId()), HttpStatus.CREATED);
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
    ResponseEntity<AirportMessageResponse> deleteAirportById(@PathVariable String id) {
        airportService.deleteAirportById(id);
        return new ResponseEntity<>(new AirportMessageResponse("Airport " + id + " deleted successfully"), HttpStatus.OK);
    }
}
