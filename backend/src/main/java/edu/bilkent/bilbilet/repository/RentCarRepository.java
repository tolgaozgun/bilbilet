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
import edu.bilkent.bilbilet.model.RentDetail;

@Qualifier("rent_car_repo")
@Repository
public class RentCarRepository {

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
    
    public List<Car> findAvailableCarsByProperties(Map<String, Object> properties) {
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

    public RentDetail save(RentDetail rentDetail) throws Exception {
        try {
            String sql = "INSERT INTO RentDetail (start_date, end_date, user_id, company_car_id) " +
                     "VALUES (?, ?, ?, ?)";
        
            KeyHolder keyHolder = new GeneratedKeyHolder();
            
            jdbcTemplate.update((PreparedStatementCreator) connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"rent_id"});
                ps.setTimestamp(1, rentDetail.getStartDate());
                ps.setTimestamp(2, rentDetail.getEndDate());
                ps.setInt(3, rentDetail.getUserId());
                ps.setInt(4, rentDetail.getCompanyCarId());
                return ps;
            }, keyHolder);
            
            int generatedId = keyHolder.getKey().intValue();
            
            rentDetail.setCompanyCarId(generatedId);
            
            return rentDetail;
        } catch (Exception e) {
            throw new Exception("Rent detail cannot be added " + e);
        }        
    }

    public List<Car> findRentDetailByTravller(int userId) {
        String sql = "SELECT * FROM RentDetails WHERE user_id = ?";

        List<Car> cars = jdbcTemplate.query(sql, carRowMapper, userId);

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