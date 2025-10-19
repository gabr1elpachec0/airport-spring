package com.airport.project.controllers.requests.flights;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public record FlightUpdateRequest(
        Optional<LocalDateTime> departureDate,
        Optional<LocalDateTime> arrivalDate,
        Optional<UUID> departureAirportId,
        Optional<UUID> arrivalAirportId,
        Optional<UUID> airplaneId) {}
