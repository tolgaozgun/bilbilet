package edu.bilkent.bilbilet.repository.journey;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import edu.bilkent.bilbilet.model.JourneyPlan;
import edu.bilkent.bilbilet.model.User;

@Qualifier("journey_plan_repo")
@Repository
public class JourneyPlanRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;


    private RowMapper<JourneyPlan> journeyPlanRowMapper = (rs, rowNum) -> {
        JourneyPlan jp = new JourneyPlan();
        jp.setJourneyPlanId(rs.getInt("journey_plan_id"));
        jp.setPlanTitle(rs.getString("plan_title"));
        jp.setUserId(rs.getInt("user_id"));
        return jp;
    };
    
    public Optional<JourneyPlan> createJourneyPlan(JourneyPlan jp) {
        final String CREATE_JOURNEY_PLAN_QUERY = "INSERT INTO JourneyPlan (plan_title, user_id) VALUES (?, ?)";
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update((PreparedStatementCreator) connection -> {
                PreparedStatement ps = connection.prepareStatement(CREATE_JOURNEY_PLAN_QUERY, new String[]{"journey_plan_id"});
                ps.setString(1, jp.getPlanTitle());
                ps.setInt(2, jp.getUserId());
                return ps;
            }, keyHolder);
            
            int generatedId = keyHolder.getKey().intValue();
            
            jp.setJourneyPlanId(generatedId);
            
            return Optional.of(jp);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Optional<JourneyPlan> deleteJourneyPlan(int jpId) {
        final String DELETE_JOURNEY_PLAN_QUERY = "DELETE FROM JourneyPlan WHERE journey_plan_id = ?";
        try {
            Optional<JourneyPlan> deletedPlan = Optional.of(jdbcTemplate.queryForObject(
                    DELETE_JOURNEY_PLAN_QUERY, journeyPlanRowMapper, jpId));
            return deletedPlan;
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Optional<List<JourneyPlan>> getJourneyPlans(int userId) {
        final String GET_JOURNEY_PLAN_FOR_USER_QUERY = "SELECT * FROM JourneyPlan WHERE user_id = ?";
        try {
            Optional<List<JourneyPlan>> jps = Optional.of(jdbcTemplate.query(
                    GET_JOURNEY_PLAN_FOR_USER_QUERY, journeyPlanRowMapper, userId));
            return jps;
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Optional<JourneyPlan> getJourneyPlan(int jpId) {
        final String GET_JOURNEY_PLAN_QUERY = "SELECT * FROM JourneyPlan WHERE journey_plan_id = ?";
        try {
            Optional<JourneyPlan> jp = Optional.of(jdbcTemplate.queryForObject(
                    GET_JOURNEY_PLAN_QUERY, journeyPlanRowMapper, jpId));
            return jp;
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
