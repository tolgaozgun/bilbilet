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
import edu.bilkent.bilbilet.model.Car;
import edu.bilkent.bilbilet.model.CompanyCar;

@Qualifier("company_car_repo")
@Repository
public class CompanyCarRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<CompanyCar> carRowMapper = (rs, rowNum) -> {
        CompanyCar cc = new CompanyCar();
        cc.setCarId(rs.getInt("car_id"));
        cc.setCompanyCarId(rs.getInt("company_id"));
        cc.setCompanyCarId(rs.getInt("company_car_id"));
        cc.setAddressId(rs.getInt("address_id"));
        cc.setPricePerDay(rs.getBigDecimal("price_per_day"));
        cc.setPhotoUrl(rs.getString("photo_url"));
        cc.setWebsiteUrl(rs.getString("website_url"));
        return cc;
    };

    public CompanyCar save(Car car) throws Exception {
        try {
            String sql = "INSERT INTO CompanyCar (car_id, company_id, address_id, price_per_day) " +
                     "VALUES (?, ?, ?, ?)";
        
            KeyHolder keyHolder = new GeneratedKeyHolder();
            
            jdbcTemplate.update((PreparedStatementCreator) connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"car_id"});
                ps.setInt(2, car.getCapacity());
                ps.setString(3, car.getGear());
                ps.setString(5, car.getModel());
                ps.setString(6, car.getBrand());
                ps.setString(7, car.getCategory());
                ps.setString(8, car.getFuelType().toString());
                ps.setString(9, car.getPhotoUrl());
                ps.setString(10, car.getWebsiteUrl());
                return ps;
            }, keyHolder);
            
            int generatedId = keyHolder.getKey().intValue();
            
            car.setCarId(generatedId);
            
            return car;
        } catch (Exception e) {
            throw new Exception("New car cannot be added due to " + e);
        }        
    }
}
