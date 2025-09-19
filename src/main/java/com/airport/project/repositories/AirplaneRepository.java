package com.airport.project.repositories;

import com.airport.project.entities.AirplaneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AirplaneRepository extends JpaRepository<AirplaneEntity, UUID> {
}
