package edu.bilkent.bilbilet.repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import edu.bilkent.bilbilet.model.Address;

@Qualifier("address_repo")
@Repository
public class AddressRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Address> addressRowMapper = (rs, rowNum) -> {
        Address address = new Address();
        address.setAddressId(rs.getInt("address_id"));
        address.setCity(rs.getString("city"));
        address.setCountry(rs.getString("country"));
        address.setLatitude(rs.getBigDecimal("latitude"));
        address.setLongitude(rs.getBigDecimal("longitude"));
        return address;
    };

    public List<Address> findAllAddress() {
        String sql = "SELECT * FROM Address";
        try {
            return jdbcTemplate.query(sql, addressRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Optional<Address> findAddressById(int id) {
        String sql = "SELECT * FROM Address WHERE address_id = ?";

        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, addressRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public Optional<Address> findAddressByCityCountry(String city, String country) {
        String sql = "SELECT * FROM Address WHERE city = ? AND country = ?";

        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, addressRowMapper, city, country));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public Address save(Address address) { // check if exist????????????
        String sql = "INSERT INTO Address (city, country, longitude, latitude) "
                +
                "VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] { "address_id" });
            ps.setString(1, address.getCity());
            ps.setString(2, address.getCountry());
            ps.setBigDecimal(3, address.getLongitude());
            ps.setBigDecimal(4, address.getLatitude());
            return ps;
        }, keyHolder);

        int generatedId = keyHolder.getKey().intValue();

        address.setAddressId(generatedId);
        Address new_address = findAddressById(address.getAddressId()).isPresent()
                ? findAddressById(address.getAddressId()).get()
                : null;

        return new_address;
    }

    public boolean existsById(int id) {
        Optional<Address> address = findAddressById(id);
        return address.isPresent();
    }

    public boolean existsByCityCountry(String city, String country) {
        Optional<Address> address = findAddressByCityCountry(city, country);
        return address.isPresent();
    }
}