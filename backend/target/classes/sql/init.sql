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
    user_id INT NOT NULL,
    company_title VARCHAR(255) UNIQUE NOT NULL,
    address TEXT NOT NULL,
    type VARCHAR(255) NOT NULL,
    contact_information TEXT,
    business_registration VARCHAR(255) NOT NULL,
    balance DECIMAL(10, 2) NOT NULL,
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
    country VARCHAR(80) NOT NULL,
    city VARCHAR(120) NOT NULL,
    latitude DECIMAL(9, 6) NOT NULL,
    longitude DECIMAL(9, 6) NOT NULL,
    PRIMARY KEY (address_id),
    CONSTRAINT latitude_valid_constraint CHECK (latitude BETWEEN -90 AND 90),
    CONSTRAINT longitude_valid_constraint CHECK (longitude BETWEEN -180 AND 180)
);

CREATE TABLE IF NOT EXISTS CarBrand (
    brand VARCHAR(255) NOT NULL,
    PRIMARY KEY (brand)
);

CREATE TABLE IF NOT EXISTS Car (
    car_id INT NOT NULL AUTO_INCREMENT,
    capacity INT NOT NULL,
    gear VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    brand VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    fuel_type VARCHAR(255) NOT NULL,
    photo_url VARCHAR(255),
    website_url VARCHAR(255),
    PRIMARY KEY (car_id),
    FOREIGN KEY (brand) REFERENCES CarBrand(brand)
);

CREATE TABLE IF NOT EXISTS CompanyCar (
    company_car_id INT NOT NULL AUTO_INCREMENT,
    car_id INT NOT NULL,
    company_id INT NOT NULL,
    address_id INT NOT NULL,
    photo_url VARCHAR(255),
    website_url VARCHAR(255),
    price_per_day DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (company_car_id),
    FOREIGN KEY (company_id) REFERENCES Company(company_id),
    FOREIGN KEY (address_id) REFERENCES Address(address_id),
    CONSTRAINT price_check CHECK (price_per_day > 0)
);