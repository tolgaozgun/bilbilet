DROP TRIGGER IF EXISTS check_date_range_overlap_before_rent_car ;
//

CREATE TRIGGER check_date_range_overlap_before_rent_car
BEFORE INSERT ON RentDetail
FOR EACH ROW
BEGIN
    DECLARE overlapping_count INT;

    SELECT COUNT(*) INTO overlapping_count
    FROM RentDetail
    WHERE start_date <= NEW.end_date
        AND end_date >= NEW.start_date
        AND company_car_id = NEW.company_car_id;

    IF overlapping_count > 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Error: Date range overlaps with existing data';
    END IF;
END ;
//

DROP TRIGGER IF EXISTS check_purchase_ticket_overlap_date ;
//

CREATE TRIGGER check_purchase_ticket_overlap_date
BEFORE UPDATE ON Ticket
FOR EACH ROW
BEGIN
    DECLARE overlapping_count INT;
    IF NEW.ticket_status = 'PURCHASED' THEN
        
        SELECT COUNT(*) INTO overlapping_count
        FROM Ticket t
        INNER JOIN Fare f ON f.fare_id = t.fare_id
        WHERE t.traveler_id = NEW.traveler_id
            AND f.departure_time <= (SELECT estimated_arrival_time FROM Fare WHERE fare_id = NEW.fare_id)
            AND f.estimated_arrival_time >= (SELECT departure_time FROM Fare WHERE fare_id = NEW.fare_id);

        IF overlapping_count > 0 THEN
            SIGNAL SQLSTATE '45000'
                SET MESSAGE_TEXT = 'Error: User has overlapping ticket date/time';
        END IF;
    END IF;
END;
//

DROP TRIGGER IF EXISTS create_tickets_based_on_fare ;
//

CREATE TRIGGER create_tickets_based_on_fare AFTER INSERT ON Fare
FOR EACH ROW
BEGIN
    DECLARE config_id INT;
    DECLARE vehicle_id INT;
    DECLARE seat_rows INT;
    DECLARE seat_columns INT;
    DECLARE premium_econ_class_after INT;
    DECLARE business_class_after INT;
    DECLARE first_class_after INT;
    DECLARE row_counter INT DEFAULT 1;
    DECLARE col_counter INT DEFAULT 1;

    -- Set seat config id of the company vehicle
    SELECT seat_configuration_id INTO config_id FROM CompanyVehicle WHERE vehicle_id = NEW.vehicle_id;
    
    SELECT total_rows, total_columns, premium_econ_class_after, business_class_after, first_class_after
        INTO seat_rows, seat_columns, premium_econ_class_after, business_class_after, first_class_after
        FROM SeatConfiguration
        WHERE seat_configuration_id = config_id;

    -- Create new tickets based on the seat configuration
    WHILE row_counter <= seat_rows DO
        WHILE col_counter <= seat_columns DO
            INSERT INTO Ticket (ticket_status, seat_type, fare_id, seat_row, seat_column)
            VALUES ('AVAILABLE', 
                    CASE
                        WHEN row_counter <= first_class_after THEN 'FIRST_CLASS'
                        WHEN row_counter <= business_after THEN 'BUSINESS'
                        WHEN row_counter <= premium_econ_after THEN 'PREMIUM_ECONOMY'
                        ELSE 'ECONOMY'
                    END,
                    NEW.fare_id,
                    row_counter,
                    col_counter);
            SET col_counter = col_counter + 1;
        END WHILE;
        
        SET col_counter = 1;
        SET row_counter = row_counter + 1;
    END WHILE;
END
//