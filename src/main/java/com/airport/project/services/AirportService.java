package com.airport.project.services;

import com.airport.project.dtos.AirportDTO;
import com.airport.project.controllers.requests.airports.AirportCreateRequest;
import com.airport.project.controllers.requests.airports.AirportUpdateRequest;
import com.airport.project.entities.AirportEntity;
import com.airport.project.exceptions.AlreadyExistsException;
import com.airport.project.exceptions.NotFoundException;
import com.airport.project.repositories.AirportRepository;
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

        if (airportEntity.isPresent()) throw new AlreadyExistsException("Airport already exists.");

        AirportEntity registeredAirportEntity = airportRepository.save(new AirportEntity(payload));

        return registeredAirportEntity.toAirport();
    }

    public List<AirportDTO> getAllAirports() {
        return airportRepository.findAll()
                .stream().map(AirportEntity::toAirport).toList();
    }

    public AirportDTO getAirportById(String id) {
        Optional<AirportEntity> airportEntity = airportRepository.findById(id);

        if (airportEntity.isEmpty()) throw new NotFoundException("Airport not found.");

        return airportEntity.get().toAirport();
    }

    public AirportDTO updateAirport(AirportUpdateRequest payload, String id) {
        Optional<AirportEntity> airportEntity = airportRepository.findById(id);

        if (airportEntity.isEmpty()) throw new NotFoundException("Airport not found.");

        AirportEntity airport = airportEntity.get();
        airport.setName(payload.name());
        airportRepository.save(airport);

        return airport.toAirport();
    }

    public void deleteAirportById(String id) {
        Optional<AirportEntity> airportEntity = airportRepository.findById(id);

        if (airportEntity.isEmpty()) throw new NotFoundException("Airport not found.");

        airportRepository.deleteById(id);
    }
}