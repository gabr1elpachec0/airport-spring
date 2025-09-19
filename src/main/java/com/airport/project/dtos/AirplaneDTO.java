package com.airport.project.dtos;

import java.util.UUID;

public class AirplaneDTO {
    private UUID id;
    private String model;
    private UUID airline;

    public AirplaneDTO() {}

    public AirplaneDTO(UUID id, String model, UUID airline) {
        this.id = id;
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
}
