package com.airport.project.controllers.requests.airplanes;

import java.util.Optional;
import java.util.UUID;

public record AirplaneUpdateRequest(Optional<String> model, Optional<UUID> airlineId) {
}
