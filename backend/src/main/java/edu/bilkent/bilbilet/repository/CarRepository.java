package edu.bilkent.bilbilet.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

@Qualifier("car_repo")
@Repository
public class CarRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Car> carRowMapper = (rs, rowNum) -> {
        Car car = new Car();
        car.setCarId(rs.getInt("car_id"));
        car.setCapacity(rs.getInt("capacity"));
        car.setGear(GearType.valueOf(rs.getString("gear")));
        car.setModel(rs.getString("model"));
        car.setBrand(rs.getString("brand"));
        car.setCategory(CarCategoryType.valueOf(rs.getString("category")));
        car.setFuelType(FuelType.valueOf(rs.getString("fuel_type")));
        car.setPhotoUrl(rs.getString("photo_url"));
        car.setWebsiteUrl(rs.getString("website_url"));
        return car;
    };    
    
    public List<Car> findCarByProperties(Map<String, Object> properties) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM Car ");
        List<Object> parameterValues = new ArrayList<>();
        boolean andNeeded = false;

        if (properties.isEmpty()) {
            return jdbcTemplate.query(sqlBuilder.toString(), carRowMapper);
        }

        sqlBuilder.append(" WHERE ");

        for (String property : properties.keySet()) {
            if (andNeeded) {
                sqlBuilder.append(" AND ");
            }
            sqlBuilder.append(property).append(" = ?");
            parameterValues.add(properties.get(property));
            andNeeded = true;
        }
        
        String sql = sqlBuilder.toString();
        
        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                for (int i = 0; i < parameterValues.size(); i++) {
                    ps.setObject(i + 1, parameterValues.get(i));
                }
            }
        }, carRowMapper); // check if empty
    }

    public Car save(Car car) throws Exception {
        try {
            String sql = "INSERT INTO Car (capacity, gear, model, brand, category, fuel_type, photo_url, website_url) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
            KeyHolder keyHolder = new GeneratedKeyHolder();
            
            jdbcTemplate.update((PreparedStatementCreator) connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"car_id"});
                ps.setInt(1, car.getCapacity());
                ps.setString(2, car.getGear().toString());
                ps.setString(3, car.getModel());
                ps.setString(4, car.getBrand());
                ps.setString(5, car.getCategory().toString());
                ps.setString(6, car.getFuelType().toString());
                ps.setString(7, car.getPhotoUrl());
                ps.setString(8, car.getWebsiteUrl());
                return ps;
            }, keyHolder);
            
            int generatedId = keyHolder.getKey().intValue();
            
            car.setCarId(generatedId);
            
            return car;
        } catch (Exception e) {
            throw new Exception("New car cannot be added due to " + e);
        }        
    }

    public List<Car> findCarsByModelAndBrandAndFuelType(String model, String brand, FuelType fuelType) {
        String sql = "SELECT * FROM Car WHERE model = ? AND brand = ? AND fuel_type = ?";

        List<Car> cars = jdbcTemplate.query(sql, carRowMapper, model, brand, fuelType.toString());

        return cars;
    }

    public boolean carExistByModelAndBrandAndFuelType(String model, String brand, FuelType fuelType) {
        try {
            String sql = "SELECT COUNT(*) FROM Car WHERE model = ? AND brand = ? AND fuel_type = ?";
            int count = jdbcTemplate.queryForObject(sql, Integer.class, model, brand, fuelType.toString());
            return count > 0;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public boolean carExistById(int carId) {
        try {
            String sql = "SELECT COUNT(*) FROM Car WHERE car_id = ?";
            int count = jdbcTemplate.queryForObject(sql, Integer.class, carId);
            return count > 0;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}