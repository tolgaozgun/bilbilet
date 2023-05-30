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

    private RowMapper<CompanyCar> companyCarRowMapper = (rs, rowNum) -> {
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
}
