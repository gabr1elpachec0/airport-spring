package com.airport.project.services;

import com.airport.project.controllers.requests.airlines.AirlineCreateRequest;
import com.airport.project.dtos.AirlineDTO;
import com.airport.project.entities.AirlineEntity;
import com.airport.project.exceptions.AlreadyExistsException;
import com.airport.project.repositories.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirlineService {
    @Autowired
    private AirlineRepository airlineRepository;

    public AirlineDTO createAirline(AirlineCreateRequest payload) {
        Optional<AirlineEntity> airlineEntity = airlineRepository.findByName(payload.name());

        if (airlineEntity.isPresent()) throw new AlreadyExistsException("Airline already exists.");

        AirlineEntity registeredAirlineEntity = airlineRepository.save(new AirlineEntity(payload.name()));

        return registeredAirlineEntity.toAirline();
    }
}
