package edu.bilkent.bilbilet.repository;

import org.springframework.beans.factory.annotation.Qualifier;

import java.sql.PreparedStatement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import edu.bilkent.bilbilet.model.CompanyCar;
import edu.bilkent.bilbilet.repository.rowmapper.BilBiletRowMapper;
import edu.bilkent.bilbilet.repository.rowmapper.CompanyCarRM;
import edu.bilkent.bilbilet.request.AddCompanyCar;

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
        return cc;
    };

    @Deprecated
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
            
            car.setCompanyCarId(generatedId);
            
            return car;
        } catch (Exception e) {
            throw new Exception("New car cannot be added due to " + e);
        }        
    }
    
    public int save(AddCompanyCar car) throws Exception {
        try {
            String sql = "INSERT INTO CompanyCar (car_id, company_id, address_id, price_per_day) " +
                     "VALUES (?, ?, SELECT address_id FROM Address WHERE country = ? AND city = ?, ?)";
        
            KeyHolder keyHolder = new GeneratedKeyHolder();
            
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"company_car_id"});
                ps.setInt(1, car.getCarId());
                ps.setInt(2, car.getCompanyId());
                ps.setString(3, car.getCountry());
                ps.setString(4, car.getCity());
                ps.setBigDecimal(5, car.getPricePerDay());
                return ps;
            }, keyHolder);
            
            int generatedId = keyHolder.getKey().intValue();

            return generatedId;
        } catch (Exception e) {
            throw new Exception("New car cannot be added due to " + e);
        }        
    }

    public List<CompanyCar> getAll() {
        String sql = "SELECT * FROM CompanyCar";
        return jdbcTemplate.query(sql, companyCarRowMapper);
    }

    public List<CompanyCarRM> findByCompanyId(int companyId) {
        String sql = "SELECT * FROM CompanyCar cc INNER JOIN Car c ON c.car_id = cc.car_id INNER JOIN Address a ON a.address_id = cc.address_id WHERE cc.company_id = ?";
        return jdbcTemplate.query(sql, BilBiletRowMapper.COMPANY_CAR_MAPPED_RM, companyId);
    }

    public List<CompanyCarRM> findByCompanyCarId(int companyCarId) {
        String sql = "SELECT * FROM CompanyCar cc INNER JOIN Car c ON c.car_id = cc.car_id INNER JOIN Address a ON a.address_id = cc.address_id WHERE cc.company_car_id = ?";
        return jdbcTemplate.query(sql, BilBiletRowMapper.COMPANY_CAR_MAPPED_RM, companyCarId);
    }

    public boolean existByCompanyCarId(int companyCarId) {
        String sql = "SELECT * FROM CompanyCar cc WHERE cc.company_car_id = ?";
        return !(jdbcTemplate.query(sql, companyCarRowMapper, companyCarId).isEmpty());
    }
}
