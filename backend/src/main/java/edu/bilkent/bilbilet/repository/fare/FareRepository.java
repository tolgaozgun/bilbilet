package edu.bilkent.bilbilet.repository.fare;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import edu.bilkent.bilbilet.model.Fare;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

@Qualifier("fare_repo")
@Repository
public class FareRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Fare> fareRowMapper = (rs, rowNum) -> {
        Fare fare = new Fare();
        fare.setFareId(rs.getInt("fare_id"));
        fare.setEstimatedDepTime(rs.getTimestamp("departure_time"));
        fare.setEstimatedArrTime(rs.getTimestamp("estimated_arrival_time"));
        fare.setPrice(rs.getBigDecimal("price"));
        fare.setCompanyId(rs.getInt("company_id"));
        fare.setVehicleId(rs.getInt("vehicle_id"));
        fare.setDepStationId(rs.getInt("dep_stat_id"));
        fare.setArrStationId(rs.getInt("arrive_stat_id"));
        return fare;
    };

    public Optional<Fare> createFare(Fare newFare) throws Exception {
        String sql = "INSERT INTO Fare (departure_time, estimated_arrival_time, price, company_id, vehicle_id, dep_stat_id, arrive_stat_id)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("departure_time", newFare.getEstimatedDepTime());
        parameters.addValue("estimated_arrival_time", newFare.getEstimatedArrTime());
        parameters.addValue("price", newFare.getPrice());
        parameters.addValue("company_id", newFare.getCompanyId());
        parameters.addValue("vehicle_id", newFare.getVehicleId());
        parameters.addValue("dep_stat_id", newFare.getDepStationId());
        parameters.addValue("arrive_stat_id", newFare.getArrStationId());

        try {
            jdbcTemplate.update(sql, parameters, keyHolder);
            
            int fareId = keyHolder.getKey().intValue();
            Fare addedFare = newFare;
            addedFare.setFareId(fareId);
            
            return Optional.of(addedFare);
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public List<Fare> getFaresByCompanyId(int companyId) throws Exception {
        String sql = "SELECT * FROM Fare WHERE company_id = ?";
    
        List<Fare> fares = jdbcTemplate.query(sql, fareRowMapper, companyId);

        return fares;
    }
}
