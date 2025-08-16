package com.airport.project.services.airports;

import com.airport.project.dtos.airports.AirportDTO;
import com.airport.project.controllers.airports.requests.AirportCreateRequest;
import com.airport.project.controllers.airports.requests.AirportUpdateRequest;
import com.airport.project.entities.airports.AirportEntity;
import com.airport.project.exceptions.airports.AirportAlreadyExistsException;
import com.airport.project.exceptions.airports.AirportNotFoundException;
import com.airport.project.repositories.airports.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {
    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public AirportDTO createAirport(AirportCreateRequest payload) {
        Optional<AirportEntity> airportEntity = airportRepository.findById(payload.id());

        if (airportEntity.isPresent()) throw new AirportAlreadyExistsException("Airport already exists.");

        AirportEntity registeredAirportEntity = airportRepository.save(new AirportEntity(payload));

        return registeredAirportEntity.toAirport();
    }

    public List<AirportDTO> getAllAirports() {
        return airportRepository.findAll()
                .stream().map(AirportEntity::toAirport).toList();
    }

    public AirportDTO getAirportById(String id) {
        Optional<AirportEntity> airportEntity = airportRepository.findById(id);

        if (airportEntity.isEmpty()) throw new AirportNotFoundException("Airport not found.");

        return airportEntity.get().toAirport();
    }

    public AirportDTO updateAirport(AirportUpdateRequest payload, String id) {
        Optional<AirportEntity> airportEntity = airportRepository.findById(id);

        if (airportEntity.isEmpty()) throw new AirportNotFoundException("Airport not found.");

        AirportEntity airport = airportEntity.get();
        airport.setName(payload.name());
        airportRepository.save(airport);

        return airport.toAirport();
    }

    public void deleteAirportById(String id) {
        Optional<AirportEntity> airportEntity = airportRepository.findById(id);

        if (airportEntity.isEmpty()) throw new AirportNotFoundException("Airport not found.");

        airportRepository.deleteById(id);
    }
}