package com.airport.project.repositories;

import com.airport.project.entities.AirplaneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AirplaneRepository extends JpaRepository<AirplaneEntity, UUID> {
    @Query("SELECT airplane FROM AirplaneEntity airplane WHERE airplane.airline = :airline")
    List<AirplaneEntity> findAllByAirlineId(@Param("airline") UUID airline);
}
