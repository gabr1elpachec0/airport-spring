package com.airport.project.entities;

import com.airport.project.dtos.FlightDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "flights")
public class FlightEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "departure_date")
    private LocalDateTime departureDate;

    @Column(name = "arrival_date")
    private LocalDateTime arrivalDate;

    @ManyToOne
    @JoinColumn(name = "departure_airport", nullable = false)
    private AirportEntity departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrival_airport", nullable = false)
    private AirportEntity arrivalAirport;

    @ManyToOne
    @JoinColumn(name = "airplane", nullable = false)
    private AirplaneEntity airplane;

    public FlightEntity() {}

    public FlightEntity(LocalDateTime departureDate, LocalDateTime arrivalDate, AirportEntity departureAirport,
                        AirportEntity arrivalAirport, AirplaneEntity airplane) {
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.airplane = airplane;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public AirportEntity getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(AirportEntity departureAirport) {
        this.departureAirport = departureAirport;
    }

    public AirportEntity getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(AirportEntity arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public AirplaneEntity getAirplane() {
        return airplane;
    }

    public void setAirplane(AirplaneEntity airplane) {
        this.airplane = airplane;
    }

    public FlightDTO toFlight() {
        return new FlightDTO(id, departureDate, arrivalDate, departureAirport.toAirport(), arrivalAirport.toAirport(),
                airplane.toAirplane());
    }
}
