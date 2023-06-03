package edu.bilkent.bilbilet.repository.fare;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import edu.bilkent.bilbilet.enums.VehicleType;
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
        if (existsById(newFare.getFareId())) {
            return updateFare(newFare);
        }
        
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

    public Optional<Fare> updateFare(Fare newFare) throws Exception {
        String sql = "UPDATE Fare SET departure_time = ?, estimated_arrival_time = ?, price = ?, company_id = ?, vehicle_id = ?, dep_stat_id = ?, arrive_stat_id = ? " +
                     "WHERE fare_id = ?";
    
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("departure_time", newFare.getEstimatedDepTime());
        parameters.addValue("estimated_arrival_time", newFare.getEstimatedArrTime());
        parameters.addValue("price", newFare.getPrice());
        parameters.addValue("company_id", newFare.getCompanyId());
        parameters.addValue("vehicle_id", newFare.getVehicleId());
        parameters.addValue("dep_stat_id", newFare.getDepStationId());
        parameters.addValue("arrive_stat_id", newFare.getArrStationId());
        parameters.addValue("fare_id", newFare.getFareId());
    
        try {
            int affectedRows = jdbcTemplate.update(sql, parameters, keyHolder);
            
            if (affectedRows < 1) {
                return Optional.empty();
            }
                
            int fareId = keyHolder.getKey().intValue();
            Fare updatedFare = newFare;
            updatedFare.setFareId(fareId);

            return Optional.of(updatedFare);
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    
        return Optional.empty();
    }

    public Optional<Fare> updateFareById(Fare newFare, int fareToUpdateId) throws Exception {
        String sql = "UPDATE Fare SET departure_time = ?, estimated_arrival_time = ?, price = ?, company_id = ?, vehicle_id = ?, dep_stat_id = ?, arrive_stat_id = ? " +
                     "WHERE fare_id = ?";
    
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("departure_time", newFare.getEstimatedDepTime());
        parameters.addValue("estimated_arrival_time", newFare.getEstimatedArrTime());
        parameters.addValue("price", newFare.getPrice());
        parameters.addValue("company_id", newFare.getCompanyId());
        parameters.addValue("vehicle_id", newFare.getVehicleId());
        parameters.addValue("dep_stat_id", newFare.getDepStationId());
        parameters.addValue("arrive_stat_id", newFare.getArrStationId());
        parameters.addValue("fare_id", fareToUpdateId);
    
        try {
            int affectedRows = jdbcTemplate.update(sql, parameters, keyHolder);
            
            if (affectedRows < 1) {
                return Optional.empty();
            }
                
            int fareId = keyHolder.getKey().intValue();
            Fare updatedFare = newFare;
            updatedFare.setFareId(fareId);

            return Optional.of(updatedFare);
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    
        return Optional.empty();
    }

    public boolean deleteFareById(int fareId) throws Exception {
        String sql = "DELETE FROM Fare WHERE fare_id = ?";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("fare_id", fareId);

        try {
            int affectedRows = jdbcTemplate.update(sql, parameters);
            return affectedRows > 0;
        }
        catch (EmptyResultDataAccessException e) {
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    
        return false;
    }

    public List<Fare> getFaresByCompanyId(int companyId) throws Exception {
        String sql = "SELECT * FROM Fare WHERE company_id = ?";
    
        try {
            return jdbcTemplate.query(sql, fareRowMapper, companyId);
        }
        catch (EmptyResultDataAccessException erda) {
            return Collections.emptyList();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    public List<Fare> getFaresByFareId(int fareId) throws Exception {
        String sql = "SELECT * FROM Fare WHERE fare_id = ?";
    
        try {
            return jdbcTemplate.query(sql, fareRowMapper, fareId);
        }
        catch (EmptyResultDataAccessException erda) {
            return Collections.emptyList();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    public boolean existsById(int fareId) throws Exception {
        try {
            List<Fare> fareList = getFaresByFareId(fareId);
            return fareList.size() > 0;
        }
        catch (EmptyResultDataAccessException erda) {
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Fare> findFareByProperties(Map<String, Object> properties, VehicleType vehicleType) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM Fare f INNER JOIN TransportVehicle t ON f.vehicle_id = t.vehicle_id WHERE t.vehicle_type = ? ");
        List<Object> parameterValues = new ArrayList<>();

        parameterValues.add(vehicleType.toString()); // VehicleType.PLANE, VehicleType.BUS 

        try {
            if (properties.isEmpty()) {
                return jdbcTemplate.query(sqlBuilder.toString(), fareRowMapper, parameterValues);
            }
    
            for (String property : properties.keySet()) {
                sqlBuilder.append(" AND ");

                sqlBuilder.append(property).append(" = ?");
                parameterValues.add(properties.get(property));
            }
            
            String sql = sqlBuilder.toString();
            
            return jdbcTemplate.query(sql, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    for (int i = 0; i < parameterValues.size(); i++) {
                        ps.setObject(i + 1, parameterValues.get(i));
                    }
                }
            }, fareRowMapper);
        }
        catch (EmptyResultDataAccessException erda) {
            return Collections.emptyList();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
}
