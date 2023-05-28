CREATE TABLE IF NOT EXISTS User (
    user_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    email VARCHAR(320) UNIQUE NOT NULL,
    telephone VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    user_type VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS Company (
    company_id INT NOT NULL AUTO_INCREMENT,
    company_title VARCHAR(255) UNIQUE NOT NULL,
    address TEXT NOT NULL,
    type VARCHAR(255) NOT NULL,
    contact_information TEXT,
    business_registration VARCHAR(255) NOT NULL,
    balance DECIMAL(10, 2) NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY (company_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE IF NOT EXISTS Traveler (
    user_id INT NOT NULL,
    nationality VARCHAR(80) NOT NULL,
    passport_number VARCHAR(30),
    balance DECIMAL(10, 2),
    TCK VARCHAR(11),
    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id),
    CONSTRAINT user_id_exists FOREIGN KEY (user_id) REFERENCES User(user_id),
    CHECK (balance >= 0)
);

-- /* Ticket and other related tables */
-- CREATE TABLE IF NOT EXISTS SeatConfiguration (
--     seat_configuration_id INT NOT NULL AUTO_INCREMENT,
--     seat_configuration_name VARCHAR(50) NOT NULL,
--     PRIMARY KEY (seat_configuration_id)
-- );


-- CREATE TABLE IF NOT EXISTS Seat (
--     seat_id INT NOT NULL AUTO_INCREMENT,
--     seat_class VARCHAR(50) NOT NULL,
--     seat_type VARCHAR(50) NOT NULL,
--     row INT NOT NULL,
--     column INT NOT NULL,
--     extra_price DECIMAL(10, 2) NOT NULL,
--     seat_configuration_id INT NOT NULL,
--     PRIMARY KEY (seat_id),
--     FOREIGN KEY (seat_configuration_id) REFERENCES SeatConfiguration(seat_configuration_id)
-- );

-- CREATE TABLE IF NOT EXISTS Address (
--     address_id INT NOT NULL AUTO_INCREMENT,
--     country VARCHAR(50) NOT NULL,
--     city VARCHAR(50) NOT NULL,
--     latitude DECIMAL(10, 8) NOT NULL,
--     longitude DECIMAL(11, 8) NOT NULL,
-- );

-- CREATE TABLE IF NOT EXISTS TransportVehicle (
--     vehicle_id INT NOT NULL AUTO_INCREMENT,
--     vehicle_type VARCHAR(50) NOT NULL,
--     seat_configuration_id INT NOT NULL,
--     company_id INT NOT NULL,
--     PRIMARY KEY (vehicle_id),
--     FOREIGN KEY (seat_configuration_id) REFERENCES SeatConfiguration(seat_configuration_id),
--     FOREIGN KEY (company_id) REFERENCES Company(company_id)
-- );

-- CREATE TABLE IF NOT EXISTS Station (
--     station_id INT NOT NULL AUTO_INCREMENT,
--     station_name VARCHAR(50) NOT NULL,
--     station_type VARCHAR(50) NOT NULL,
--     address_id INT NOT NULL,
--     PRIMARY KEY (station_id),
--     FOREIGN KEY (address_id) REFERENCES Address(address_id)
-- );

-- CREATE TABLE IF NOT EXISTS Fare (
--     fare_id INT NOT NULL AUTO_INCREMENT,
--     departure_time TIMESTAMP NOT NULL,
--     estimated_arrival_time TIMESTAMP NOT NULL,
--     price DECIMAL(10, 2) NOT NULL,
--     company_id INT NOT NULL,
--     vehicle_id INT NOT NULL,
--     dep_stat_id INT NOT NULL,
--     arrive_stat_id INT NOT NULL,
--     PRIMARY KEY (fare_id),
--     FOREIGN KEY (company_id) REFERENCES Company(company_id),
--     FOREIGN KEY (vehicle_id) REFERENCES TransportVehicle(vehicle_id),
--     FOREIGN KEY (dep_stat_id) REFERENCES Station(station_id),
--     FOREIGN KEY (arrive_stat_id) REFERENCES Station(station_id)
-- );

-- CREATE TABLE IF NOT EXISTS Ticket (
--     ticket_id INT NOT NULL AUTO_INCREMENT,
--     ticket_status VARCHAR(50) NOT NULL,
--     seat_id INT NOT NULL,
--     fare_id INT NOT NULL,
--     traveler_id INT NOT NULL,
--     PRIMARY KEY (ticket_id),
--     FOREIGN KEY (seat_id) REFERENCES Seat(seat_id),
--     FOREIGN KEY (fare_id) REFERENCES Fare(fare_id),
--     FOREIGN KEY (traveler_id) REFERENCES Traveler(user_id)
-- );

-- CREATE TABLE IF NOT EXISTS Reservation (
--     reservation_id INT NOT NULL AUTO_INCREMENT,
--     reservation_status VARCHAR(50) NOT NULL,
--     created_at TIMESTAMP NOT NULL,
--     reserved_until TIMESTAMP NOT NULL,
--     reservation_fee DECIMAL(10, 2) NOT NULL,
--     ticket_id INT NOT NULL,
--     PRIMARY KEY (reservation_id),
--     FOREIGN KEY (ticket_id) REFERENCES Ticket(ticket_id)
-- );