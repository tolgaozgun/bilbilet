package edu.bilkent.bilbilet.repository.journey;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import edu.bilkent.bilbilet.model.Journey;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

@Qualifier("journey_repo")
@Repository
public class JourneyRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;


    private RowMapper<Journey> journeyRowMapper = (rs, rowNum) -> {
        Journey journey = new Journey();
        journey.setJourney_id(0); // FIXME: Is this field important? It is generated automatically no?
        journey.setFare_id(rs.getInt("fare_id"));
        journey.setJourney_plan_id(rs.getInt("journey_plan_id"));
        journey.setJourney_title(rs.getString("journey_title"));
        return journey;
    };

    public Optional<Journey> createJourney(Journey newJourney) throws DataAccessException {
        String sql = "INSERT INTO Journey (journey_title, journey_plan_id, fare_id) VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("journey_title", newJourney.getJourney_title());
        parameters.addValue("journey_plan_id", newJourney.getJourney_plan_id());
        parameters.addValue("fare_id", newJourney.getFare_id());

        try {
            int status = namedJdbcTemplate.update(sql, parameters, keyHolder, new String[]{"journey_id"});
            Long journeyId = keyHolder.getKey().longValue();
            Optional<Journey> journey = getJourney(journeyId);
            
            return journey;
        } catch (DataAccessException e) {
            throw e;
        } catch (Exception e) {
             e.printStackTrace();
             return Optional.empty();
        }
    }

    public Optional<Journey> getJourney(Long journeyId) throws Exception, EmptyResultDataAccessException {
        String sql = "SELECT * FROM Journey WHERE journey_id = ?";

        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, journeyRowMapper, journeyId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
