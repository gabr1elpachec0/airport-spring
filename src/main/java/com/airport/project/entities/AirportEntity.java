package com.airport.project.entities;

import com.airport.project.dtos.AirportDTO;
import com.airport.project.controllers.requests.airports.AirportCreateRequest;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "airports")
public class AirportEntity {
    @Id
    @Column(name = "id", length = 5)
    private String id;
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "departureAirport")
    private List<FlightEntity> flightsDeparture = new ArrayList<>();

    @OneToMany(mappedBy = "arrivalAirport")
    private List<FlightEntity> flightsArrival = new ArrayList<>();

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
