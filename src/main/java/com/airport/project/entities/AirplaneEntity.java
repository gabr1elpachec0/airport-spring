package com.airport.project.entities;

import com.airport.project.dtos.AirplaneDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
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

    @ManyToOne
    @JoinColumn(name = "airline", nullable = false)
    private AirlineEntity airline;

    @OneToMany(mappedBy = "airplane")
    private List<FlightEntity> flights = new ArrayList<>();

    public AirplaneEntity() {}

    public AirplaneEntity(String model, AirlineEntity airline) {
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

    public AirlineEntity getAirline() {
        return airline;
    }

    public void setAirline(AirlineEntity airline) {
        this.airline = airline;
    }

    public List<FlightEntity> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightEntity> flights) {
        this.flights = flights;
    }

    public AirplaneDTO toAirplane() {
        return new AirplaneDTO(id, model, airline.toAirline());
    }
}
