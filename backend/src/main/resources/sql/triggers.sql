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
