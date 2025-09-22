package com.airport.project.services;

import com.airport.project.controllers.requests.airplanes.AirplaneCreateRequest;
import com.airport.project.controllers.requests.airplanes.AirplaneUpdateRequest;
import com.airport.project.controllers.responses.CreateResponse;
import com.airport.project.dtos.AirplaneDTO;
import com.airport.project.entities.AirlineEntity;
import com.airport.project.entities.AirplaneEntity;
import com.airport.project.exceptions.NotFoundException;
import com.airport.project.repositories.AirlineRepository;
import com.airport.project.repositories.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
                new AirplaneEntity(payload.model(), airlineEntity.get())
        );

        return new CreateResponse(registeredAirplane.getId().toString());
    }

    public List<AirplaneDTO> getAllAirplanes() {
        List<AirplaneEntity> airplaneEntities = airplaneRepository.findAll();

        return airplaneEntities.stream()
                .map(it -> new AirplaneDTO(it.getId(), it.getModel(), it.getAirline().toAirline()))
                .toList();
    }

    public List<AirplaneDTO> getAllAirplanesByAirlineId(UUID airlineId) {
        Optional<AirlineEntity> airlineEntity = airlineRepository.findById(airlineId);

        if (airlineEntity.isEmpty()) throw new NotFoundException("Airline does not exist.");

        List<AirplaneEntity> airplaneEntities = airlineEntity.get().getAirplanes();

        return airplaneEntities.stream()
                .map(it -> new AirplaneDTO(it.getId(), it.getModel(), it.getAirline().toAirline()))
                .toList();
    }

    public AirplaneDTO getAirplaneById(UUID id) {
        Optional<AirplaneEntity> airplaneEntity = airplaneRepository.findById(id);

        if (airplaneEntity.isEmpty()) throw new NotFoundException("Airplane does not exist.");

        return airplaneEntity.get().toAirplane();
    }

    public AirplaneDTO updateAirplane(UUID id, AirplaneUpdateRequest payload) {
        Optional<AirplaneEntity> airplaneEntity = airplaneRepository.findById(id);

        if (airplaneEntity.isEmpty()) throw new NotFoundException("Airplane does not exit.");

        AirplaneEntity airplane = airplaneEntity.get();
        if (payload.model().isPresent()) airplane.setModel(payload.model().get());
        if (payload.airlineId().isPresent()) {
            Optional<AirlineEntity> airlineEntity = airlineRepository.findById(payload.airlineId().get());
            if (airlineEntity.isEmpty()) throw new NotFoundException("Airline does not exist.");
            airplane.setAirline(airlineEntity.get());
        }
        airplaneRepository.save(airplane);

        return airplane.toAirplane();
    }

    public void deleteAirplane(UUID id) {
        Optional<AirplaneEntity> airplaneEntity = airplaneRepository.findById(id);

        if (airplaneEntity.isEmpty()) throw new NotFoundException("Airplane does not exist.");

        airplaneRepository.delete(airplaneEntity.get());
    }
}
