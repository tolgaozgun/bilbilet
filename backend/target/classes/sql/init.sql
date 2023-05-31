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

CREATE TABLE IF NOT EXISTS Address (
    address_id INT NOT NULL AUTO_INCREMENT,
    country VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    latitude DECIMAL(10, 8) NOT NULL,
    longitude DECIMAL(11, 8) NOT NULL,
    PRIMARY KEY (address_id)
);

CREATE TABLE IF NOT EXISTS Station (
    station_id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    abbreviation VARCHAR(50) NOT NULL,
    station_type VARCHAR(50) NOT NULL,
    address_id INT NOT NULL,
    PRIMARY KEY (station_id),
    FOREIGN KEY (address_id) REFERENCES Address(address_id)
);