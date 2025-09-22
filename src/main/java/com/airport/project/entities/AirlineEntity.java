package com.airport.project.entities;

import com.airport.project.dtos.AirlineDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "airlines")
public class AirlineEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "airline", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AirplaneEntity> airplanes = new ArrayList<>();

    public AirlineEntity() {}

    public AirlineEntity(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AirplaneEntity> getAirplanes() {
        return airplanes;
    }

    public void setAirplanes(List<AirplaneEntity> airplanes) {
        this.airplanes = airplanes;
    }

    public AirlineDTO toAirline() {
        return new AirlineDTO(id, name);
    }
}
