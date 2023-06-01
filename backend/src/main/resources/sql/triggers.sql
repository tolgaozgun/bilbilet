-- CREATE TRIGGER check_and_insert_address_before_company_car_insert
-- BEFORE INSERT ON CompanyCar
-- FOR EACH ROW
-- BEGIN
--     DECLARE address_id INT;

--     -- Check if the address exists
--     SELECT address_id INTO address_id
--     FROM address
--     WHERE city = NEW.city AND country = NEW.country
--     LIMIT 1;

--     -- If address does not exist, insert a new row
--     IF address_id IS NULL THEN
--         INSERT INTO address (country, city, latitude, longitude)
--         VALUES (NEW.country, NEW.city, 0, 0);

--         SET address_id = LAST_INSERT_ID();
--     END IF;

--     -- Set the address_id for the new company_car row
--     SET NEW.addressId = address_id;
-- END;
