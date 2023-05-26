package edu.bilkent.bilbilet.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
        hotel.setAddressId((rs.getInt("adress_id")));
        return hotel;
    };

    public Hotel findHotelByAddressId(int addressId) {
        String sql = "SELECT * FROM Hotel WHERE adress_id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, hotelRowMapper, addressId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
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
        String sql = "INSERT INTO Hotel (name, avg_price, telephone, rating, website_url, cover_photo_url, photo_url) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(
                sql,
                hotel.getName(),
                hotel.getAvgPrice(),
                hotel.getTelephone(),
                hotel.getRating(),
                hotel.getWebsiteUrl(),
                hotel.getCoverPhotoUrl(),
                hotel.getPhotoUrl());

        Hotel new_hotel = findHotelByName(hotel.getName()).isPresent() ? findHotelByName(hotel.getName()).get() : null;
        return new_hotel;
    }

    public boolean existsByName(String name) {
        Optional<Hotel> hotel = findHotelByName(name);

        return hotel.isPresent();
    }
}
