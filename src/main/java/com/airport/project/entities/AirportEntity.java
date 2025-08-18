package com.airport.project.entities;

import com.airport.project.dtos.AirportDTO;
import com.airport.project.controllers.requests.airports.AirportCreateRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "airports")
public class AirportEntity {
    @Id
    @Column(name = "id", length = 5)
    private String id;
    @Column(name = "name", nullable = false)
    private String name;

    public AirportEntity() {}

    public AirportEntity(AirportCreateRequest payload) {
        this.id = payload.id();
        this.name = payload.name();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AirportDTO toAirport() {
        return new AirportDTO(id, name);
    }
}
