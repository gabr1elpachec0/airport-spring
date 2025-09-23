package com.airport.project.services;

import com.airport.project.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;
}
