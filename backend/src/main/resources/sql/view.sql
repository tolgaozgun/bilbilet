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
