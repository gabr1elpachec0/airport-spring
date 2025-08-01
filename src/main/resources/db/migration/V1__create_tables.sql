CREATE TABLE airports (
    id VARCHAR(5) PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE airlines (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE airplanes (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    model VARCHAR(255),
    airline UUID,
    CONSTRAINT fk_airplanes_airline_airlines FOREIGN KEY (airline) REFERENCES airlines (id)
);

CREATE TABLE flights (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    departure_date TIMESTAMP,
    arrival_date TIMESTAMP,
    departure_airport VARCHAR(5),
    arrival_airport VARCHAR(5),
    airplane UUID,
    CONSTRAINT fk_flights_departure_airport_airports FOREIGN KEY (departure_airport) REFERENCES airports (id),
    CONSTRAINT fk_flights_arrival_airport_airports FOREIGN KEY (arrival_airport) REFERENCES airports (id),
    CONSTRAINT fk_flights_airplane_airplanes FOREIGN KEY (airplane) REFERENCES airplanes (id)
);