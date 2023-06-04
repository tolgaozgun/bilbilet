package edu.bilkent.bilbilet.repository.fare;

import java.sql.PreparedStatement;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import edu.bilkent.bilbilet.model.VehicleSeatConfig;

@Qualifier("config_repo")
@Repository
public class VehicleSeatConfigRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<VehicleSeatConfig> configRowMapper = (rs, rowNum) -> {
        VehicleSeatConfig config = new VehicleSeatConfig();
        config.setConfigId(rs.getInt("seat_configuration_id"));
        config.setConfigName(rs.getString("seat_configuration_name"));
        config.setSeatingArrangement(rs.getString("seating_arrangement"));
        config.setConfigTotalRows(rs.getInt("total_rows"));
        config.setConfigTotalColumns(rs.getInt("total_columns"));
        config.setPremiumEconomyClassAfter(rs.getInt("premium_econ_class_after"));
        config.setBusinessClassAfter(rs.getInt("business_class_after"));
        config.setFirstClassAfter(rs.getInt("first_class_after"));
        return config;
    };

    public Optional<VehicleSeatConfig> createConfig(VehicleSeatConfig newConfig) throws Exception {
        if (existsById(newConfig.getConfigId())) {
            return updateConfig(newConfig);
        }
    
        String sql = "INSERT INTO SeatConfiguration "
                + "(seat_configuration_name, seating_arrangement, "
                + "total_rows, total_columns, premium_econ_class_after, "
                + "business_class_after, first_class_after)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";
    
        KeyHolder keyHolder = new GeneratedKeyHolder();
    
        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[] { "seat_configuration_id" });
                ps.setString(1, newConfig.getConfigName());
                ps.setString(2, newConfig.getSeatingArrangement());
                ps.setInt(3, newConfig.getConfigTotalRows());
                ps.setInt(4, newConfig.getConfigTotalColumns());
                ps.setInt(5, newConfig.getPremiumEconomyClassAfter());
                ps.setInt(6, newConfig.getBusinessClassAfter());
                ps.setInt(7, newConfig.getFirstClassAfter());
                return ps;
            }, keyHolder);
    
            int configId = keyHolder.getKey().intValue();
            VehicleSeatConfig addedConfig = newConfig;
            addedConfig.setConfigId(configId);
    
            return Optional.of(addedConfig);
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    
        return Optional.empty();
    }

    public Optional<VehicleSeatConfig> updateConfig(VehicleSeatConfig newConfig) throws Exception {
        String sql = "UPDATE SeatConfiguration "
                    + "SET seat_configuration_name = ?, "
                    + "seating_arrangement = ?, "
                    + "total_rows = ?, "
                    + "total_columns = ?, "
                    + "premium_econ_class_after = ?, "
                    + "business_class_after = ?, "
                    + "first_class_after = ? "
                    + "WHERE seat_configuration_id = " + newConfig.getConfigId();
    
        KeyHolder keyHolder = new GeneratedKeyHolder();
    
        try {
            int affectedRows = jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[] { "seat_configuration_id" });
                ps.setString(1, newConfig.getConfigName());
                ps.setString(2, newConfig.getSeatingArrangement());
                ps.setInt(3, newConfig.getConfigTotalRows());
                ps.setInt(4, newConfig.getConfigTotalColumns());
                ps.setInt(5, newConfig.getPremiumEconomyClassAfter());
                ps.setInt(6, newConfig.getBusinessClassAfter());
                ps.setInt(7, newConfig.getFirstClassAfter());
                return ps;
            }, keyHolder);
    
            if (affectedRows < 1) {
                return Optional.empty();
            }
    
            int configId = keyHolder.getKey().intValue();
            VehicleSeatConfig updatedConfig = newConfig;
            updatedConfig.setConfigId(configId);
    
            return Optional.of(updatedConfig);
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    
        return Optional.empty();
    }

    public Optional<VehicleSeatConfig> updateFareById(VehicleSeatConfig newConfig, int configToUpdateId) throws Exception {
        String sql = "UPDATE SeatConfiguration "
                    + "SET seat_configuration_name = ?, "
                    + "seating_arrangement = ?, "
                    + "total_rows = ?, "
                    + "total_columns = ?, "
                    + "premium_econ_class_after = ?, "
                    + "business_class_after = ?, "
                    + "first_class_after = ? "
                    + "WHERE seat_configuration_id = " + configToUpdateId;
    
        KeyHolder keyHolder = new GeneratedKeyHolder();
    
        try {
            int affectedRows = jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[] { "seat_configuration_id" });
                ps.setString(1, newConfig.getConfigName());
                ps.setString(2, newConfig.getSeatingArrangement());
                ps.setInt(3, newConfig.getConfigTotalRows());
                ps.setInt(4, newConfig.getConfigTotalColumns());
                ps.setInt(5, newConfig.getPremiumEconomyClassAfter());
                ps.setInt(6, newConfig.getBusinessClassAfter());
                ps.setInt(7, newConfig.getFirstClassAfter());
                return ps;
            }, keyHolder);
    
            if (affectedRows < 1) {
                return Optional.empty();
            }
    
            int configId = keyHolder.getKey().intValue();
            VehicleSeatConfig updatedConfig = newConfig;
            updatedConfig.setConfigId(configId);
    
            return Optional.of(updatedConfig);
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    
        return Optional.empty();
    }

    public boolean deleteConfigById(int configId) throws Exception {
        String sql = "DELETE FROM SeatConfiguration WHERE seat_configuration_id = ?";

        try {
            int affectedRows = jdbcTemplate.update(sql, configId);
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

    public Optional<VehicleSeatConfig> getConfigByConfigId(int configId) throws Exception {
        String sql = "SELECT * FROM SeatConfiguration WHERE seat_configuration_id = ?";
    
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, configRowMapper, configId));
        }
        catch (EmptyResultDataAccessException erda) {
            return Optional.empty();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public boolean existsById(int configId) throws Exception {
        try {
            return getConfigByConfigId(configId).isPresent();
        }
        catch (EmptyResultDataAccessException erda) {
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public Optional<VehicleSeatConfig> getConfigByVehicleId(int vehicleId) {
        String sql = "SELECT s.* FROM SeatConfiguration s "
                    + "INNER JOIN CompanyVehicle c ON s.seat_configuration_id = c.seat_configuration_id "
                    + "WHERE cv.vehicle_id = ?";
        
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, configRowMapper, vehicleId));
        }
        catch (EmptyResultDataAccessException erda) {
            return Optional.empty();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    
        return Optional.empty();
    }
}
