package edu.bilkent.bilbilet.repository;

import edu.bilkent.bilbilet.enums.VehicleType;
import edu.bilkent.bilbilet.model.Company;
import edu.bilkent.bilbilet.model.vehicles.CompanyBus;
import edu.bilkent.bilbilet.model.vehicles.CompanyPlane;
import edu.bilkent.bilbilet.model.vehicles.CompanyVehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Qualifier("company_vehicle_repo")
@Repository
public class CompanyVehicleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private RowMapper<CompanyVehicle> companyVehicleRowMapper = (rs, rowNum) -> {
        CompanyVehicle companyVehicle = new CompanyVehicle();
        companyVehicle.setVehicleId(rs.getInt("vehicle_id"));
        companyVehicle.setCapacity(rs.getInt("capacity"));
        companyVehicle.setVehicleType(VehicleType.valueOf(rs.getString("vehicle_type")));
        companyVehicle.setVehicleReferenceId(rs.getInt("vehicle_reference_id"));
        companyVehicle.setSeatConfigurationId(rs.getInt("seat_configuration_id"));
        companyVehicle.setCompanyId(rs.getInt("company_id"));
        return companyVehicle;
    };

    private RowMapper<CompanyBus> companyBusRowMapper = (rs, rowNum) -> {
        CompanyBus companyBus = new CompanyBus();
        companyBus.setBusId(rs.getInt("bus_id"));
        companyBus.setPlateNumber(rs.getString("plate_number"));
        return companyBus;
    };

    private RowMapper<CompanyPlane> companyPlaneRowMapper = (rs, rowNum) -> {
        CompanyPlane companyPlane = new CompanyPlane();
        companyPlane.setPlaneId(rs.getInt("plane_id"));
        companyPlane.setTailNumber(rs.getString("tail_number"));
        return companyPlane;
    };

    public List<CompanyVehicle> findAllCompanyVehicles() {
        String sql = "SELECT * FROM CompanyVehicle";
        try {
            return jdbcTemplate.query(sql, companyVehicleRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }


    public List<CompanyVehicle> findAllCompanyVehiclesByCompanyId(int companyId) {
        String sql = "SELECT * FROM CompanyVehicle WHERE company_id = ?";
        try {
            return jdbcTemplate.query(sql, companyVehicleRowMapper, companyId);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Optional<CompanyVehicle> findCompanyVehicleById(int id) {
        String sql = "SELECT * FROM CompanyVehicle WHERE vehicle_id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, companyVehicleRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<CompanyBus> findCompanyBusById(int id) {
        String sql = "SELECT * FROM CompanyBus WHERE bus_id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, companyBusRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<CompanyPlane> findCompanyPlaneById(int id) {
        String sql = "SELECT * FROM CompanyPlane WHERE plane_id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, companyPlaneRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public CompanyBus save(CompanyBus companyBus) {
        String sql = "INSERT INTO CompanyBus (plate_number) "
                + "VALUES (?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"bus_id"});
            ps.setString(1, companyBus.getPlateNumber());
            return ps;
        }, keyHolder);

        int generatedId = keyHolder.getKey().intValue();
        companyBus.setBusId(generatedId);

        return findCompanyBusById(companyBus.getBusId()).orElse(null);
    }

    public CompanyPlane save(CompanyPlane companyPlane) {
        String sql = "INSERT INTO CompanyPlane (tail_number) "
                + "VALUES (?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"plane_id"});
            ps.setString(1, companyPlane.getTailNumber());
            return ps;
        }, keyHolder);

        int generatedId = keyHolder.getKey().intValue();
        companyPlane.setPlaneId(generatedId);

        return findCompanyPlaneById(companyPlane.getPlaneId()).orElse(null);
    }

    public CompanyVehicle save(CompanyVehicle companyVehicle) {
        String sql = "INSERT INTO CompanyVehicle (capacity, vehicle_type, vehicle_reference_id, seat_configuration_id, company_id) "
                + "VALUES (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"vehicle_id"});
            ps.setInt(1, companyVehicle.getCapacity());
            ps.setString(2, companyVehicle.getVehicleType().name());
            ps.setInt(3, companyVehicle.getVehicleReferenceId());
            ps.setInt(4, companyVehicle.getSeatConfigurationId());
            ps.setInt(5, companyVehicle.getCompanyId());
            return ps;
        }, keyHolder);

        int generatedId = keyHolder.getKey().intValue();
        companyVehicle.setVehicleId(generatedId);

        return findCompanyVehicleById(companyVehicle.getVehicleId()).orElse(null);
    }

    public boolean existsById(int id) {
        return findCompanyVehicleById(id).isPresent();
    }
}