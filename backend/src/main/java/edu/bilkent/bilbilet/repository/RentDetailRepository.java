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
import edu.bilkent.bilbilet.repository.rowmapper.CompanyCarRM;
import edu.bilkent.bilbilet.repository.rowmapper.RentDetailRM;

@Qualifier("rent_detail_repo")
@Repository
public class RentDetailRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<RentDetail> rentDetailRM = (rs, rowNum) -> {
        RentDetail rd = new RentDetail();
        rd.setRentId(rs.getInt("rd.rent_id"));
        rd.setUserId(rs.getInt("rd.user_id"));
        rd.setCompanyCarId(rs.getInt("rd.company_car_id"));
        rd.setStartDate(rs.getTimestamp("rd.start_date"));
        rd.setEndDate(rs.getTimestamp("rd.end_date"));
        return rd;
    };  

    private RowMapper<RentDetailRM> rentDetailDetailedRM = (rs, rowNum) -> {
        RentDetailRM rd = new RentDetailRM();
        rd.setRentId(rs.getInt("rd.rent_id"));
        rd.setUserId(rs.getInt("rd.user_id"));
        rd.setStartDate(rs.getTimestamp("rd.start_date"));
        rd.setEndDate(rs.getTimestamp("rd.end_date"));

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
        rd.setCompanyCar(cc);
        return rd;
    };
    
    public List<RentDetailRM> findAvailableCarsByProperties(Map<String, Object> properties) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM Car c INNER JOIN CompanyCar cc ON c.car_id = cc.car_id");
        // sqlBuilder.append("INNER JOIN RentDetail rd ON rd.company_car_id = cc.company_car_id");

        List<Object> parameterValues = new ArrayList<>();
        boolean andNeeded = false;

        if (properties.isEmpty()) {
            return jdbcTemplate.query(sqlBuilder.toString(), rentDetailDetailedRM);
        }

        sqlBuilder.append(" WHERE ");

        for (String property : properties.keySet()) {
            if (andNeeded) {
                sqlBuilder.append(" AND ");
            }

            if (property.equals("start_date"))
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

    public List<RentDetailRM> findRentDetailByTraveler(int userId) {
        String sql = "SELECT * FROM RentDetails rd WHERE rd.user_id = ? "
                   + "INNER JOIN CompanyCar cc ON rd.company_car_id = cc.company_car_id INNER JOIN Car c ON cc.car_id = c.car_id";

        List<RentDetailRM> rents = jdbcTemplate.query(sql, rentDetailDetailedRM, userId);

        return rents;
    }
}