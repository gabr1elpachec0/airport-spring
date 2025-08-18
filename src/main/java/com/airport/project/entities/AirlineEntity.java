package com.airport.project.entities;

import com.airport.project.dtos.AirlineDTO;
import jakarta.persistence.*;

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

    public AirlineDTO toAirline() {
        return new AirlineDTO(id, name);
    }
}
