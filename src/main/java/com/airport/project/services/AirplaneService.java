package com.airport.project.services;

import com.airport.project.controllers.requests.airplanes.AirplaneCreateRequest;
import com.airport.project.controllers.responses.CreateResponse;
import com.airport.project.dtos.AirplaneDTO;
import com.airport.project.entities.AirlineEntity;
import com.airport.project.entities.AirplaneEntity;
import com.airport.project.exceptions.NotFoundException;
import com.airport.project.repositories.AirlineRepository;
import com.airport.project.repositories.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirplaneService {
    @Autowired
    private AirplaneRepository airplaneRepository;

    @Autowired
    private AirlineRepository airlineRepository;

    public CreateResponse createAirplane(AirplaneCreateRequest payload) {
        Optional<AirlineEntity> airlineEntity = airlineRepository.findById(payload.airline());

        if (airlineEntity.isEmpty()) throw new NotFoundException("Airline does not exist.");

        AirplaneEntity registeredAirplane = airplaneRepository.save(
                new AirplaneEntity(payload.model(), payload.airline())
        );

        return new CreateResponse(registeredAirplane.getId().toString());
    }
}
