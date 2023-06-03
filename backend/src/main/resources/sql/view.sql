CREATE OR REPLACE VIEW TripReviewDetailedPublicView AS
SELECT 
    t.ticket_id AS ticket_id,
    r.review_id AS review_id,
    r.user_id AS user_id,
    r.comment AS comment,
    r.punctuality AS punctuality,
    r.cleanliness AS cleanliness,
    r.customer_service AS customer_service,
    depStation.title AS dep_station_title,
    depStation.abbreviation AS dep_station_abbr,
    arriveStation.title AS arr_station_title,
    arriveStation.abbreviation AS arr_station_abbr,
    f.departure_time AS departure_time,
    f.estimated_arrival_time AS estimated_arrival_time,
    company.company_title AS company_title,
    company.company_id AS company_id
FROM TripReview tr
INNER JOIN Review r ON r.review_id = tr.review_id
INNER JOIN Ticket t ON t.ticket_id = tr.ticket_id
INNER JOIN Fare f ON f.fare_id = t.fare_id
INNER JOIN Station depStation ON depStation.station_id = f.dep_stat_id
INNER JOIN Station arriveStation ON arriveStation.station_id = f.arrive_stat_id
INNER JOIN Company company ON company.company_id = f.company_id;

CREATE OR REPLACE VIEW TripReviewDetailedPrivateView AS
SELECT 
    t.ticket_id AS ticket_id,
    r.review_id AS review_id,
    r.user_id AS user_id,
    r.comment AS comment,
    r.punctuality AS punctuality,
    r.cleanliness AS cleanliness,
    r.customer_service AS customer_service,
    depStation.title AS dep_station_title,
    depStation.abbreviation AS dep_station_abbr,
    arriveStation.title AS arr_station_title,
    arriveStation.abbreviation AS arr_station_abbr,
    f.departure_time AS departure_time,
    f.estimated_arrival_time AS estimated_arrival_time,
    t.ticket_status AS ticket_status,
    f.price AS price,
    company.company_title AS company_title,
    company.company_id AS company_id
FROM TripReview tr
INNER JOIN Review r ON r.review_id = tr.review_id
INNER JOIN Ticket t ON t.ticket_id = tr.ticket_id
INNER JOIN Fare f ON f.fare_id = t.fare_id
INNER JOIN Station depStation ON depStation.station_id = f.dep_stat_id
INNER JOIN Station arriveStation ON arriveStation.station_id = f.arrive_stat_id
INNER JOIN Company company ON company.company_id = f.company_id;

CREATE OR REPLACE VIEW CompanyReviewDetailedView AS
SELECT 
    r.review_id AS review_id,
    r.user_id AS user_id,
    r.comment AS comment,
    r.punctuality AS punctuality,
    r.cleanliness AS cleanliness,
    r.customer_service AS customer_service,
    company.company_title AS company_title,
    company.company_id AS company_id
FROM CompanyReview cr
INNER JOIN Review r ON r.review_id = cr.review_id
INNER JOIN Company company ON company.company_id = cr.company_id;

CREATE OR REPLACE VIEW DisplayFareTicketsView AS
SELECT 
    t.ticket_id AS ticket_id,
    t.ticket_status AS ticket_status,
    t.seat_type AS seat_type,
    t.seat_row AS seat_row,
    t.seat_column AS seat_column,
    t.fare_id AS fare_id,
    CASE
        WHEN t.seat_type = 'PREMIUM_ECONOMY' THEN (f.price + f.premium_econ_extra_price)
        WHEN t.seat_type = 'BUSINESS' THEN (f.price + f.business_extra_price)
        WHEN t.seat_type = 'FIRST_CLASS' THEN (f.price + f.first_class_extra_price)
        ELSE f.price
    END AS total_price
FROM Ticket t
INNER JOIN Fare f ON f.fare_id = t.fare_id;

CREATE OR REPLACE VIEW DisplayUserTicketsView AS
SELECT 
    t.ticket_id AS ticket_id,
    t.ticket_status AS ticket_status,
    t.seat_type AS seat_type,
    t.seat_row AS seat_row,
    t.seat_column AS seat_column,
    t.fare_id AS fare_id,
    t.traveler_id AS traveler_id,
    CASE
        WHEN t.seat_type = 'PREMIUM_ECONOMY' THEN (f.price + f.premium_econ_extra_price)
        WHEN t.seat_type = 'BUSINESS' THEN (f.price + f.business_extra_price)
        WHEN t.seat_type = 'FIRST_CLASS' THEN (f.price + f.first_class_extra_price)
        ELSE f.price
    END AS total_price,
    f.departure_time AS departure_time,
    f.estimated_arrival_time AS arrival_time,
    c.company_title AS company_title,
    depStation.title AS dep_title,
    depStation.abbreviation AS dep_abbreviation,
    arrStation.title AS arr_title,
    arrStation.abbreviation AS arr_abbreviation
FROM Ticket t
INNER JOIN Fare f ON f.fare_id = t.fare_id
INNER JOIN Company c ON c.company_id = f.company_id
INNER JOIN Station depStation ON depStation.station_id = f.dep_stat_id
INNER JOIN Station arrStation ON arrStation.station_id = f.arrive_stat_id;
