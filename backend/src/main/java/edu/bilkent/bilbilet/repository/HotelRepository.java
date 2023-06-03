package edu.bilkent.bilbilet.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import edu.bilkent.bilbilet.model.Hotel;

@Qualifier("hotel_repo")
@Repository
public class HotelRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Hotel> hotelRowMapper = (rs, rowNum) -> {
        Hotel hotel = new Hotel();
        hotel.setHotelId(rs.getInt("hotel_id"));
        hotel.setName(rs.getString("name"));
        hotel.setAvgPrice((rs.getBigDecimal("avg_price")));
        hotel.setTelephone(rs.getString("telephone"));
        hotel.setRating(rs.getBigDecimal("rating"));
        hotel.setWebsiteUrl(rs.getString("website_url"));
        hotel.setCoverPhotoUrl(rs.getString("cover_photo_url"));
        hotel.setPhotoUrl(rs.getString("photo_url"));
        hotel.setAddressId((rs.getInt("address_id")));
        return hotel;
    };

    public List<Hotel> findHotelsByAddressId(int addressId) {
        String sql = "SELECT * FROM Hotel WHERE address_id = ?";

        try {
            return jdbcTemplate.query(sql, hotelRowMapper, addressId);
        } catch (EmptyResultDataAccessException e) {
            List<Hotel> emptyList = Collections.<Hotel>emptyList();
            return emptyList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Hotel> emptyList = Collections.<Hotel>emptyList();
        return emptyList;
    }

    public Optional<Hotel> findHotelByName(String name) {
        String sql = "SELECT * FROM Hotel WHERE name = ?";

        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, hotelRowMapper, name));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public Hotel save(Hotel hotel) { // check if exist????????????
        String sql = "INSERT INTO Hotel (name, avg_price, telephone, rating, website_url, cover_photo_url, photo_url, address_id) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] { "hotel_id" });
            ps.setString(1, hotel.getName());
            ps.setBigDecimal(2, hotel.getAvgPrice());
            ps.setString(3, hotel.getTelephone());
            ps.setBigDecimal(4, hotel.getRating());
            ps.setString(5, hotel.getWebsiteUrl());
            ps.setString(6, hotel.getCoverPhotoUrl());
            ps.setString(7, hotel.getPhotoUrl());
            ps.setInt(8, hotel.getAddressId());
            return ps;
        }, keyHolder);

        int generatedId = keyHolder.getKey().intValue();
        Hotel new_hotel = findHotelByName(hotel.getName()).isPresent() ? findHotelByName(hotel.getName()).get() : null;
        return new_hotel;
    }

    public boolean existsByName(String name) {
        Optional<Hotel> hotel = findHotelByName(name);

        return hotel.isPresent();
    }

    public List<Hotel> findHotelByProperties(Map<String, Object> properties) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM Hotel ");
        List<Object> parameterValues = new ArrayList<>();
        boolean andNeeded = false;

        if (properties.isEmpty()) {
            return jdbcTemplate.query(sqlBuilder.toString(), hotelRowMapper);
        }

        sqlBuilder.append(" WHERE ");

        for (String property : properties.keySet()) {
            Object param = properties.get(property);
            String paramStr = (String) param;
            if (paramStr.isEmpty() || paramStr.isBlank()) {
                continue;
            }
            
            if (andNeeded) {
                sqlBuilder.append(" AND ");
            }
            sqlBuilder.append(property);
            // Contains range
            if (property.compareTo("avg_price") == 0 || property.compareTo("rating") == 0) {
                sqlBuilder.append(" BETWEEN ? AND ? ");
                String[] rangeList = paramStr.split(",");
                parameterValues.add(rangeList[0]);
                parameterValues.add(rangeList[1]);
            } 
            // Contains single value
            else {
                sqlBuilder.append(" = ? ");
                parameterValues.add(param);
            }

            andNeeded = true;
        }
        
        String sql = sqlBuilder.toString();
        System.out.println("Get hotels query: " + sql);
        System.out.println("Param values: " + parameterValues.toString());

        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                for (int i = 0; i < parameterValues.size(); i++) {
                    ps.setObject(i + 1, parameterValues.get(i));
                }
            }
        }, hotelRowMapper); // check if empty
    }
}
