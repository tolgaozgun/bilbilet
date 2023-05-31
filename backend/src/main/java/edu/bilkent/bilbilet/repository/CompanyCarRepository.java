package edu.bilkent.bilbilet.repository;

import org.springframework.beans.factory.annotation.Qualifier;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import edu.bilkent.bilbilet.enums.FuelType;
import edu.bilkent.bilbilet.enums.GearType;
import edu.bilkent.bilbilet.model.Car;
import edu.bilkent.bilbilet.model.CarCategoryType;
import edu.bilkent.bilbilet.model.CompanyCar;
import edu.bilkent.bilbilet.repository.rowmapper.CompanyCarRM;

@Qualifier("company_car_repo")
@Repository
public class CompanyCarRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<CompanyCar> companyCarRowMapper = (rs, rowNum) -> {
        CompanyCar cc = new CompanyCar();
        cc.setCarId(rs.getInt("car_id"));
        cc.setCompanyId(rs.getInt("company_id"));
        cc.setCompanyCarId(rs.getInt("company_car_id"));
        cc.setAddressId(rs.getInt("address_id"));
        cc.setPricePerDay(rs.getBigDecimal("price_per_day"));
        cc.setPhotoUrl(rs.getString("photo_url"));
        cc.setWebsiteUrl(rs.getString("website_url"));
        return cc;
    };
    
    private RowMapper<CompanyCarRM> companyCarRMCar = (rs, rowNum) -> {
        CompanyCarRM cc = new CompanyCarRM();
        cc.setCompanyId(rs.getInt("cc.company_id"));
        cc.setCompanyCarId(rs.getInt("cc.company_car_id"));
        cc.setAddressId(rs.getInt("cc.address_id"));
        cc.setPricePerDay(rs.getBigDecimal("cc.price_per_day"));
        cc.setPhotoUrl(rs.getString("cc.photo_url"));
        cc.setWebsiteUrl(rs.getString("cc.website_url"));

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

        cc.setCar(c);
        return cc;
    };

    public CompanyCar save(CompanyCar car) throws Exception {
        try {
            String sql = "INSERT INTO CompanyCar (car_id, company_id, address_id, price_per_day) " +
                     "VALUES (?, ?, ?, ?)";
        
            KeyHolder keyHolder = new GeneratedKeyHolder();
            
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"company_car_id"});
                ps.setInt(1, car.getCarId());
                ps.setInt(2, car.getCompanyId());
                ps.setInt(3, car.getAddressId());
                ps.setBigDecimal(4, car.getPricePerDay());
                return ps;
            }, keyHolder);
            
            int generatedId = keyHolder.getKey().intValue();
            
            car.setCarId(generatedId);
            
            return car;
        } catch (Exception e) {
            throw new Exception("New car cannot be added due to " + e);
        }        
    }

    public List<CompanyCar> getAll() {
        String sql = "SELECT * FROM CompanyCar";
        return jdbcTemplate.query(sql, companyCarRowMapper);
    }

    public List<CompanyCarRM> findByCompanyId(int companyId) {
        String sql = "SELECT * FROM CompanyCar cc INNER JOIN Car c ON c.car_id = cc.car_id WHERE cc.company_id = ?";
        return jdbcTemplate.query(sql, companyCarRMCar, companyId);
    }
}
