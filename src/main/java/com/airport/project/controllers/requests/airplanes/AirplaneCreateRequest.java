package com.airport.project.controllers.requests.airplanes;

import java.util.UUID;

public record AirplaneCreateRequest(String model, UUID airline) {
}
