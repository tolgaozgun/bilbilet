package edu.bilkent.bilbilet.repository.rowmapper;

import org.springframework.jdbc.core.RowMapper;

import edu.bilkent.bilbilet.model.Address;
import edu.bilkent.bilbilet.model.Car;
import edu.bilkent.bilbilet.model.CarCategoryType;
import edu.bilkent.bilbilet.enums.FuelType;
import edu.bilkent.bilbilet.enums.GearType;

public class BilBiletRowMapper {

    public static final RowMapper<CompanyCarRM> COMPANY_CAR_MAPPED_RM = (rs, rowNum) -> {
        CompanyCarRM cc = new CompanyCarRM();
        cc.setCompanyId(rs.getInt("cc.company_id"));
        cc.setCompanyCarId(rs.getInt("cc.company_car_id"));
        cc.setPricePerDay(rs.getBigDecimal("cc.price_per_day"));

        Car c = new Car();
        c.setCarId(rs.getInt("c.car_id"));
        c.setCapacity(rs.getInt("c.capacity"));
        c.setGear(GearType.valueOf(rs.getString("c.gear")));
        c.setModel(rs.getString("c.model"));
        c.setBrand(rs.getString("c.brand"));
        c.setCategory(CarCategoryType.valueOf(rs.getString("c.category")));
        c.setFuelType(FuelType.valueOf(rs.getString("c.fuel_type")));
        c.setPhotoUrl(rs.getString("c.photo_url"));
        c.setWebsiteUrl(rs.getString("c.website_url"));

        Address a = new Address();
        a.setAddressId(rs.getInt("a.address_id"));
        a.setCity(rs.getString("a.city"));
        a.setCountry(rs.getString("a.country"));
        a.setLatitude(rs.getBigDecimal("a.latitude"));
        a.setLongitude(rs.getBigDecimal("a.longitude"));

        cc.setCar(c);
        cc.setAddress(a);
        return cc;
    };
}
