package com.airport.project.services;

import com.airport.project.controllers.requests.flights.CreateFlightRequest;
import com.airport.project.controllers.responses.CreateResponse;
import com.airport.project.dtos.FlightDTO;
import com.airport.project.entities.AirplaneEntity;
import com.airport.project.entities.AirportEntity;
import com.airport.project.entities.FlightEntity;
import com.airport.project.exceptions.NotFoundException;
import com.airport.project.repositories.AirplaneRepository;
import com.airport.project.repositories.AirportRepository;
import com.airport.project.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private AirplaneRepository airplaneRepository;

    public CreateResponse createFlight(CreateFlightRequest payload) {
        Optional<AirportEntity> departureAirportEntity = airportRepository.findById(payload.departureAirportId());
        if (departureAirportEntity.isEmpty()) throw new NotFoundException("Departure airport does not exist.");

        Optional<AirportEntity> arrivalAirportEntity = airportRepository.findById(payload.arrivalAirportId());
        if (arrivalAirportEntity.isEmpty()) throw new NotFoundException("Arrival airport does not exist.");

        Optional<AirplaneEntity> airplaneEntity = airplaneRepository.findById(payload.airplaneId());
        if (airplaneEntity.isEmpty()) throw new NotFoundException("Airplane does not exist.");

        FlightEntity flightEntity = new FlightEntity(
                payload.departureDate(),
                payload.arrivalDate(),
                departureAirportEntity.get(),
                arrivalAirportEntity.get(),
                airplaneEntity.get()
        );

        FlightEntity registeredFlight = flightRepository.save(flightEntity);

        return new CreateResponse(registeredFlight.getId().toString());
    }
}
