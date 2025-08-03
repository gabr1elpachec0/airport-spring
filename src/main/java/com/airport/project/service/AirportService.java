package com.airport.project.service;

import com.airport.project.dto.AirportDTO;
import com.airport.project.entity.Airport;
import com.airport.project.repository.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirportService {
    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public AirportDTO createAirport(AirportDTO body) {
        Airport airport = new Airport(body.getId(), body.getName());

        Airport registeredAirport = airportRepository.save(airport);

        return new AirportDTO(registeredAirport.getId(), registeredAirport.getName());
    }

    public AirportDTO getAirportById(String id) {
        Airport registeredAirport = airportRepository.getReferenceById(id);

        return new AirportDTO(registeredAirport.getId(), registeredAirport.getName());
    }

    public List<AirportDTO> getAllAirports() {
        List<Airport> airports = airportRepository.findAll();
        return airports.stream()
                .map(airport -> new AirportDTO(airport.getId(), airport.getName()))
                .collect(Collectors.toList());
    }

    public void deleteAirportById(String id) {
        airportRepository.deleteById(id);
    }
}
