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
