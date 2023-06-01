package edu.bilkent.bilbilet.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.bilkent.bilbilet.model.Fare;

@Repository
public class FareRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Fare> fareRowMapper = (rs, rowNum) -> {
        Fare f = new Fare();
        f.setArriveStatId(rs.getInt("arrive_stat_id"));
        f.setCompanyId(rs.getInt("company_id"));
        f.setDepStatId(rs.getInt("dep_stat_id"));
        f.setDepartureTime(rs.getTimestamp("departure_time"));
        f.setEstimatedArrivalTime(rs.getTimestamp("estimated_arrival_time"));
        f.setFareId(rs.getInt("fare_id"));
        f.setPrice(rs.getBigDecimal("price"));
        f.setVehicleId(rs.getInt("vehicle_id"));
        return f;
    };

    public Optional<Fare> findFare(int fareId) {
        String sql = "SELECT * FROM Fare WHERE fare_id = ?";
        try {
            Optional<Fare> fare = Optional.of(jdbcTemplate.queryForObject(sql, fareRowMapper, fareId));
            return fare;
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
