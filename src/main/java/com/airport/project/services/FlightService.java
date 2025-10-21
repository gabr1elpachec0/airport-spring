package com.airport.project.services;

import com.airport.project.controllers.requests.flights.CreateFlightRequest;
import com.airport.project.controllers.requests.flights.FlightUpdateRequest;
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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private AirplaneRepository airplaneRepository;

    public CreateResponse createFlight(CreateFlightRequest payload) {
        Optional<AirportEntity> departureAirportEntity = airportRepository.findById(payload.departureAirport());
        if (departureAirportEntity.isEmpty()) throw new NotFoundException("Departure airport does not exist.");

        Optional<AirportEntity> arrivalAirportEntity = airportRepository.findById(payload.arrivalAirport());
        if (arrivalAirportEntity.isEmpty()) throw new NotFoundException("Arrival airport does not exist.");

        Optional<AirplaneEntity> airplaneEntity = airplaneRepository.findById(payload.airplane());
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

    public List<FlightDTO> getAllFlights() {
        List<FlightEntity> flightEntities = flightRepository.findAll();

        return flightEntities.stream().map(FlightEntity::toFlight).toList();
    }

    public FlightDTO getFlightById(UUID flightId) {
        Optional<FlightEntity> flightEntity = flightRepository.findById(flightId);
        if (flightEntity.isEmpty()) throw new NotFoundException("Flight does not exist.");
        FlightEntity flight = flightEntity.get();
        return flight.toFlight();
    }

    public FlightDTO updateFlight(UUID flightId, FlightUpdateRequest payload) {
        Optional<FlightEntity> flightEntity = flightRepository.findById(flightId);
        if (flightEntity.isEmpty()) throw new NotFoundException("Flight does not exist.");
        FlightEntity flight = flightEntity.get();

        if (payload.departureDate().isPresent()) flight.setDepartureDate(payload.departureDate().get());
        if (payload.arrivalDate().isPresent()) flight.setArrivalDate(payload.arrivalDate().get());
        if (payload.departureAirport().isPresent()) {
            Optional<AirportEntity> departureAirportEntity = airportRepository.findById(payload.departureAirport().get());
            if (departureAirportEntity.isEmpty()) throw new NotFoundException("Departure airport does not exist.");
            flight.setDepartureAirport(departureAirportEntity.get());
        }
        if (payload.arrivalAirport().isPresent()) {
            Optional<AirportEntity> arrivalAirportEntity = airportRepository.findById(payload.arrivalAirport().get());
            if (arrivalAirportEntity.isEmpty()) throw new NotFoundException("Arrival airport does not exist.");
            flight.setArrivalAirport(arrivalAirportEntity.get());
        }
        if (payload.airplane().isPresent()) {
            Optional<AirplaneEntity> airplaneEntity = airplaneRepository.findById(payload.airplane().get());
            if (airplaneEntity.isEmpty()) throw new NotFoundException("Airplane does not exist.");
            flight.setAirplane(airplaneEntity.get());
        }
        FlightEntity updatedFlight = flightRepository.save(flight);
        return updatedFlight.toFlight();
    }

    public void deleteFlight(UUID flightId) {
        Optional<FlightEntity> flightEntity = flightRepository.findById(flightId);
        if (flightEntity.isEmpty()) throw new NotFoundException("Flight does not exist.");
        flightRepository.delete(flightEntity.get());
    }
}
