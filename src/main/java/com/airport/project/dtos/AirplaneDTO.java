package com.airport.project.dtos;

import java.util.UUID;

public class AirplaneDTO {
    private UUID id;
    private String model;
    private AirlineDTO airline;

    public AirplaneDTO() {}

    public AirplaneDTO(UUID id, String model, AirlineDTO airline) {
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

    public AirlineDTO getAirline() {
        return airline;
    }

    public void setAirline(AirlineDTO airline) {
        this.airline = airline;
    }
}
