package com.airport.project.services;

import com.airport.project.controllers.requests.airlines.AirlineCreateRequest;
import com.airport.project.controllers.requests.airlines.AirlineUpdateRequest;
import com.airport.project.controllers.responses.CreateResponse;
import com.airport.project.dtos.AirlineDTO;
import com.airport.project.entities.AirlineEntity;
import com.airport.project.exceptions.AlreadyExistsException;
import com.airport.project.exceptions.NotFoundException;
import com.airport.project.repositories.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AirlineService {
    @Autowired
    private AirlineRepository airlineRepository;

    public CreateResponse createAirline(AirlineCreateRequest payload) {
        Optional<AirlineEntity> airlineEntity = airlineRepository.findByName(payload.name());

        if (airlineEntity.isPresent()) throw new AlreadyExistsException("Airline already exists.");

        AirlineEntity registeredAirlineEntity = airlineRepository.save(new AirlineEntity(payload.name()));

        return new CreateResponse(registeredAirlineEntity.getId().toString());
    }

    public List<AirlineDTO> getAllAirlines() {
        List<AirlineEntity> airlines = airlineRepository.findAll();

        return airlines.stream().map(AirlineEntity::toAirline).toList();
    }

    public AirlineDTO getAirlineById(UUID id) {
        Optional<AirlineEntity> airlineEntity = airlineRepository.findById(id);

        if (airlineEntity.isEmpty()) throw new NotFoundException("Airline not found.");

        AirlineEntity airline = airlineEntity.get();

        return airline.toAirline();
    }

    public AirlineDTO updateAirline(UUID id, AirlineUpdateRequest payload) {
        Optional<AirlineEntity> airlineEntity = airlineRepository.findById(id);

        if (airlineEntity.isEmpty()) throw new NotFoundException("Airline does not exist.");

        AirlineEntity airline = airlineEntity.get();
        airline.setName(payload.name());
        airlineRepository.save(airline);

        return airline.toAirline();
    }

    public void deleteAirline(UUID id) {
        Optional<AirlineEntity> airlineEntity = airlineRepository.findById(id);

        if (airlineEntity.isEmpty()) throw new NotFoundException("Airline does not exist.");

        airlineRepository.delete(airlineEntity.get());
    }
}
