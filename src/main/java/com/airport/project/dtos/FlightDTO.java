package com.airport.project.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public class FlightDTO {
    private UUID id;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private AirportDTO departureAirport;
    private AirportDTO arrivalAirport;
    private AirplaneDTO airplane;

    public FlightDTO() {}

    public FlightDTO(UUID id, LocalDateTime departureDate, LocalDateTime arrivalDate, AirportDTO departureAirport,
                     AirportDTO arrivalAirport, AirplaneDTO airplane) {
        this.id = id;
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

    public AirportDTO getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(AirportDTO departureAirport) {
        this.departureAirport = departureAirport;
    }

    public AirportDTO getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(AirportDTO arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public AirplaneDTO getAirplane() {
        return airplane;
    }

    public void setAirplane(AirplaneDTO airplane) {
        this.airplane = airplane;
    }
}
