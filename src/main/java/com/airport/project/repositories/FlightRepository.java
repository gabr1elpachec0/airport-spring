package com.airport.project.repositories;

import com.airport.project.entities.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FlightRepository extends JpaRepository<FlightEntity, UUID> {
}
