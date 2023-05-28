package edu.bilkent.bilbilet.repository.journey;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.bilkent.bilbilet.model.Journey;
import edu.bilkent.bilbilet.request.journey.CreateJourney;

@Qualifier("journey_repo")
@Repository
public class JourneyRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Journey> journeyRowMapper = (rs, rowNum) -> {
        Journey journey = new Journey();
        journey.setJourney_id(0); // FIXME: Is this field important? It is generated automatically no?
        journey.setFare_id(rs.getInt("fare_id"));
        journey.setJourney_plan_id(rs.getInt("journey_plan_id"));
        journey.setJourney_title(rs.getString("journey_title"));
        return journey;
    };

    public Optional<Journey> createJourney(Journey newJourney) {
        String sql = "INSERT INTO Journey (journey_id, journey_title, journey_plan_id, fare_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, newJourney.getJourney_id(), newJourney.getJourney_title(),
                newJourney.getJourney_plan_id(), newJourney.getFare_id());
        
        Optional<Journey> journey = getJourney(newJourney.getJourney_id());
        return journey;
    }

    public Optional<Journey> getJourney(int journey_id) {
        String sql = "SELECT * FROM Journey WHERE journey_id = ?";

        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, journeyRowMapper, journey_id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
