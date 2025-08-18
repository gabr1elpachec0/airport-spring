package com.airport.project.repositories;

import com.airport.project.entities.AirlineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirlineRepository extends JpaRepository<AirlineEntity, String> {
    @Query("SELECT airline FROM AirlineEntity airline WHERE airline.name = :name")
    Optional<AirlineEntity> findByName(@Param("name") String name);
}
