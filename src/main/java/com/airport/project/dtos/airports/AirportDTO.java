package com.airport.project.dtos.airports;

public class AirportDTO {
    private String id;
    private String name;

    public AirportDTO() {};

    public AirportDTO(String id, String name) {
        this.id = id;
        this.name = name;
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
}

