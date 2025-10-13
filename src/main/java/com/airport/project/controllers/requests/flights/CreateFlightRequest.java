package com.airport.project.controllers.requests.flights;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateFlightRequest(
        LocalDateTime departureDate,
        LocalDateTime arrivalDate,
        UUID departureAirportId,
        UUID arrivalAirportId,
        UUID airplaneId) {}
