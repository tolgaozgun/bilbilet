package edu.bilkent.bilbilet.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import edu.bilkent.bilbilet.enums.TicketStatus;
import edu.bilkent.bilbilet.model.Car;
import edu.bilkent.bilbilet.model.CarCategoryType;
import edu.bilkent.bilbilet.response.RSysReportMost;

@Qualifier("sys_report_repo")
@Repository
public class SystemReportRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private RowMapper<RSysReportMost> maxBrandRowMapper = (rs, rowNum) -> {
        RSysReportMost mb = new RSysReportMost();
        mb.setCount(rs.getInt("count"));
        mb.setTitle(rs.getString("brand"));
        return mb;
    };    

    public List<RSysReportMost> findMostRentedCarBrand() throws Exception {
        try {
            String sql = "SELECT c.brand, COUNT(rd.rent_id) AS count FROM RentDetail rd "
                       + "INNER JOIN CompanyCar cc ON cc.company_car_id = rd.company_car_id "
                       + "INNER JOIN Car c ON c.car_id = cc.car_id GROUP BY(c.brand) ORDER BY count DESC LIMIT 10;";
        
            List<RSysReportMost> maxBrands = jdbcTemplate.query(sql, maxBrandRowMapper);
            
            return maxBrands;
        } catch (Exception e) {
            throw new Exception("New car cannot be added due to " + e);
        }        
    }
    
    public List<RSysReportMost> findCompanyWithMostPurchasedTickets() throws Exception {
        try {
            String sql = "SELECT c.company_title, count(t.ticket_id) AS count FROM Ticket t INNER JOIN Fare f "
                    + "INNER JOIN Company c on f.company_id = c.company_id "
                    + "WHERE t.ticket_status = ? GROUP BY(f.company_id) "
                    + "ORDER BY count DESC LIMIT 10;";
        
            List<RSysReportMost> maxBrands = jdbcTemplate.query(sql, maxBrandRowMapper, TicketStatus.PURCHASED);
            
            return maxBrands;
        } catch (Exception e) {
            throw new Exception("New car cannot be added due to " + e);
        }        
    }

    public List<RSysReportMost> findArrivalWithMostPurchase() throws Exception {
        try {
            String sql = "SELECT arrive_stat.title, COUNT(t.ticket_id) AS count "
                       + "FROM Ticket t "
                       + "INNER JOIN Fare f ON t.fare_id = f.fare_id "
                       + "INNER JOIN Company c ON f.company_id = c.company_id "
                       + "INNER JOIN Station arrive_stat ON f.arrive_stat_id = arrive_stat.station_id "
                       + "INNER JOIN Station depart_stat ON f.dep_stat_id = depart_stat.station_id "
                       + "WHERE t.ticket_status = ? "
                       + "GROUP BY f.arrive_stat_id "
                       + "ORDER BY count DESC "
                       + "LIMIT 10;";
        
            List<RSysReportMost> maxBrands = jdbcTemplate.query(sql, maxBrandRowMapper, TicketStatus.PURCHASED);
            
            return maxBrands;
        } catch (Exception e) {
            throw new Exception("New car cannot be added due to " + e);
        }        
    }

    public List<RSysReportMost> findDestinationWithMostPurchase() throws Exception {
        try {
            String sql = "SELECT depart_stat.title, COUNT(t.ticket_id) AS count "
                       + "FROM Ticket t "
                       + "INNER JOIN Fare f ON t.fare_id = f.fare_id "
                       + "INNER JOIN Company c ON f.company_id = c.company_id "
                       + "INNER JOIN Station arrive_stat ON f.arrive_stat_id = arrive_stat.station_id "
                       + "INNER JOIN Station depart_stat ON f.dep_stat_id = depart_stat.station_id "
                       + "WHERE t.ticket_status = ? "
                       + "GROUP BY f.dep_stat_id "
                       + "ORDER BY count DESC "
                       + "LIMIT 10;";
        
            List<RSysReportMost> maxBrands = jdbcTemplate.query(sql, maxBrandRowMapper, TicketStatus.PURCHASED);
            
            return maxBrands;
        } catch (Exception e) {
            throw new Exception("New car cannot be added due to " + e);
        }        
    }
}