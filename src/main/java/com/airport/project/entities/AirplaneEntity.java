package com.airport.project.entities;

import com.airport.project.dtos.AirplaneDTO;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "airplanes")
public class AirplaneEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "model")
    private String model;

    @Column(name = "airline")
    private UUID airline;

    public AirplaneEntity() {}

    public AirplaneEntity(String model, UUID airline) {
        this.model = model;
        this.airline = airline;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public UUID getAirline() {
        return airline;
    }

    public void setAirline(UUID airline) {
        this.airline = airline;
    }

    public AirplaneDTO toAirplane() {
        return new AirplaneDTO(id, model, airline);
    }
}
