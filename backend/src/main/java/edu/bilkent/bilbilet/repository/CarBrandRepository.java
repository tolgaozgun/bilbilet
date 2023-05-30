package edu.bilkent.bilbilet.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.bilkent.bilbilet.model.CarBrand;

@Qualifier("car_brand_repo")
@Repository
public class CarBrandRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<CarBrand> carBrandRowMapper = (rs, rowNum) -> {
        CarBrand cb = new CarBrand();
        cb.setBrand(rs.getString("brand"));
        return cb;
    };    
    
    public List<CarBrand> findAll() {
        String sql = "SELECT * FROM CarBrand";
        return jdbcTemplate.query(sql, carBrandRowMapper);
    }

    public boolean brandExist(String brand) {
        try {
            String sql = "SELECT COUNT(*) FROM CarBrand WHERE brand = ?";
            int count = jdbcTemplate.queryForObject(sql, Integer.class, brand);
            return count > 0;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}