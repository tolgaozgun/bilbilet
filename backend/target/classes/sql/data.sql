INSERT IGNORE INTO CarBrand (brand) VALUES
    ('Toyota'),
    ('Honda'),
    ('Hyundai'),
    ('Mercedes'),
    ('KIA'),
    ('Volswagen'),
    ('Peguot'),
    ('Fiat'),
    ('Mini Cooper'),
    ('Ford'),
    ('Chevrolet'),
    ('BMW');

INSERT INTO User (name, surname, email, telephone, password, user_type)
VALUES ('John', 'Doe', 'johndoe@example.com', '1234567890', 'password123', 'user');

INSERT INTO Company (user_id, company_title, address, type, contact_information, business_registration, balance)
VALUES (1, 'ABC Company', '123 Main Street', 'Technology', 'info@abccompany.com', '123456789', 10000.00);

INSERT INTO Traveler (user_id, nationality, passport_number, balance, TCK)
VALUES (2, 'USA', 'P123456', 5000.00, NULL);

INSERT INTO Seat (seat_class, seat_type, `row`, `column`, extra_price, seat_configuration_id)
VALUES ('Economy', 'Window', 1, 1, 0.00, 1),
       ('Economy', 'Aisle', 1, 2, 0.00, 1),
       ('Business', 'Window', 1, 1, 50.00, 2),
       ('Business', 'Aisle', 1, 2, 50.00, 2);

INSERT INTO Address (country, city, latitude, longitude)
VALUES ('USA', 'New York', 40.7128, -74.0060),
       ('UK', 'London', 51.5074, -0.1278);

INSERT INTO Car (capacity, gear, model, brand, category, fuel_type, photo_url, website_url)
VALUES (5, 'Automatic', 'Corolla', 'Toyota', 'Sedan', 'Petrol', 'https://example.com/toyota.jpg', 'https://www.toyota.com'),
       (4, 'Automatic', 'X5', 'BMW', 'SUV', 'Diesel', 'https://example.com/bmw.jpg', 'https://www.bmw.com');

INSERT INTO CompanyCar (car_id, company_id, address_id, photo_url, website_url, price_per_day)
VALUES (1, 1, 1, 'https://example.com/car1.jpg', 'https://www.abccompany.com/car1', 75.00),
       (2, 1, 1, 'https://example.com/car2.jpg', 'https://www.abccompany.com/car2', 100.00);

INSERT INTO TransportVehicle (vehicle_type, seat_configuration_id, company_id)
VALUES ('BUS', 1, 1),
       ('PLANE', 2, 1);

INSERT INTO Station (title, abbreviation, station_type, address_id)
VALUES ('Central Station', 'CS', 'Train', 2),
       ('Bus Terminal', 'BT', 'Bus', 1);

INSERT INTO Hotel (`name`, avg_price, telephone, rating, website_url, cover_photo_url, photo_url, address_id)
VALUES ('Hotel ABC', 150.00, '1234567890', 4.5, 'https://www.hotelabc.com', 'https://example.com/hotel_abc_cover.jpg', 'https://example.com/hotel_abc.jpg', 1);

INSERT INTO Fare (departure_time, estimated_arrival_time, price, company_id, vehicle_id, dep_stat_id, arrive_stat_id)
VALUES ('2023-06-04 09:00:00', '2023-06-04 12:00:00', 50.00, 1, 1, 1, 2),
       ('2023-06-05 14:00:00', '2023-06-05 17:00:00', 75.00, 1, 2, 2, 1);

INSERT INTO JourneyPlan (plan_title, user_id)
VALUES ('Trip to New York', 1),
       ('Weekend Getaway', 1);

INSERT INTO Journey (journey_title, journey_plan_id, ticket_id)
VALUES ('New York Trip Day 1', 1, 1),
       ('Weekend Getaway Day 1', 2, 2);