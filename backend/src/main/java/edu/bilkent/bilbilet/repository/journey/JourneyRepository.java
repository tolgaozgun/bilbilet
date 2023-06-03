package edu.bilkent.bilbilet.repository.journey;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
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

import edu.bilkent.bilbilet.enums.CompanyType;
import edu.bilkent.bilbilet.enums.StationType;
import edu.bilkent.bilbilet.enums.TicketStatus;
import edu.bilkent.bilbilet.model.Journey;
import edu.bilkent.bilbilet.repository.journey.dto.JourneyDetailsRM;
import edu.bilkent.bilbilet.repository.journey.dto.NewJourneyDepArrRM;

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
        journey.setJourneyId(rs.getInt("journey_id")); // FIXME: Is this field important? It is generated automatically no?
        journey.setTicketId(rs.getInt("ticket_id"));
        journey.setJourneyPlanId(rs.getInt("journey_plan_id"));
        journey.setJourneyTitle(rs.getString("journey_title"));
        return journey;
    };

    private RowMapper<NewJourneyDepArrRM> journeyDepArrTimesRowMapper = (rs, rowNum) -> {
        NewJourneyDepArrRM depArr = new NewJourneyDepArrRM();
        depArr.setNewArrTime(rs.getTimestamp("new_arr_time"));
        depArr.setNewDepTime(rs.getTimestamp("new_dep_time"));
        return depArr;
    };

    private RowMapper<JourneyDetailsRM> journeyDetailsRM = (rs, rowNum) -> {
        JourneyDetailsRM journey = new JourneyDetailsRM();
        journey.setJourneyId(rs.getInt("journey_id"));
        journey.setJourneyTitle(rs.getString("journey_title"));
        
        journey.setTicketStatus(TicketStatus.valueOf(rs.getString("ticket_status")));
        journey.setSeatId(rs.getInt("seat_id"));
        
        journey.setDepartureTime(rs.getTimestamp("departure_time"));
        journey.setEstimatedArrivalTime(rs.getTimestamp("estimated_arrival_time"));
        journey.setPrice(rs.getBigDecimal("price"));
        
        journey.setCompanyTitle(rs.getString("company_title"));
        journey.setAddress(rs.getString("company_address"));
        journey.setCompanyType(CompanyType.valueOf(rs.getString("company_type")));
        journey.setContactInformation(rs.getString("contact_information"));
        
        journey.setStationTitle(rs.getString("station_title"));
        journey.setAbbreviation(rs.getString("station_abbreviation"));
        journey.setStationType(StationType.valueOf(rs.getString("station_type")));

        return journey;
    };

    public Optional<Journey> createJourney(Journey newJourney) throws DataAccessException {
        String sql = "INSERT INTO Journey (journey_title, journey_plan_id, ticket_id) VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("journey_title", newJourney.getJourneyTitle());
        parameters.addValue("journey_plan_id", newJourney.getJourneyPlanId());
        parameters.addValue("ticket_id", newJourney.getTicketId());

        try {
            int status = namedJdbcTemplate.update(sql, parameters, keyHolder, new String[]{"journey_id"});
            int journeyId = keyHolder.getKey().intValue();
            Optional<Journey> journey = getJourney(journeyId);
            
            return journey;
        } catch (DataAccessException e) {
            throw e;
        } catch (Exception e) {
             e.printStackTrace();
             return Optional.empty();
        }
    }

    public Optional<Journey> deleteJourney(int journeyId) {
        String sql = "DELETE FROM Journey WHERE journey_id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, journeyRowMapper, journeyId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Optional<Journey> getJourney(int journeyId) {
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

    public Optional<List<JourneyDetailsRM>> findJourneysInJourneyPlan(int journeyPlanId) {
        String FIND_JOURNEYS_IN_JOURNEY_PLAN_QUERY = "SELECT" +
                                                        " Journey.journey_id AS journey_id," +
                                                        "Journey.journey_title AS journey_title," +
                                                        "Ticket.ticket_status AS ticket_status," +
                                                        "Ticket.seat_id AS seat_id," +
                                                        "Fare.departure_time AS departure_time," +
                                                        "Fare.estimated_arrival_time AS estimated_arrival_time," +
                                                        "Fare.price AS price," +
                                                        "Company.company_title AS company_title," +
                                                        "Company.address AS company_address," +
                                                        "Company.type AS company_type," +
                                                        "Company.contact_information AS contact_information," +
                                                        "Station.title AS station_title," +
                                                        "Station.abbreviation AS station_abbreviation," +
                                                        "Station.station_type AS station_type" +
                                                    " FROM" +
                                                        "Journey" +
                                                        "JOIN JourneyPlan ON Journey.journey_plan_id = JourneyPlan.journey_plan_id" +
                                                        "JOIN Ticket ON Journey.ticket_id = Ticket.ticket_id" +
                                                        "JOIN Fare ON Ticket.fare_id = Fare.fare_id" +
                                                        "JOIN Company ON Fare.company_id = Company.company_id" +
                                                        "JOIN Station ON Fare.dep_stat_id = Station.station_id" +
                                                        "JOIN Station ON Fare.arr_stat_id = Station.station_id" +
                                                    " WHERE" +
                                                        "Journey.journey_plan_id = ?;";
        try {
            List<JourneyDetailsRM> journeys = jdbcTemplate.query(FIND_JOURNEYS_IN_JOURNEY_PLAN_QUERY, journeyDetailsRM, journeyPlanId);
            return Optional.of(journeys);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        
    }

    public Optional<NewJourneyDepArrRM> getJourneyAffiliatedTicketDepArr(Journey j) {
        final int ticketId = j.getTicketId();
        String GET_FARE_DEP_ARR_TIMES_QUERY = "SELECT F.departure_time AS new_dep_time, F.estimated_arrival_time AS new_arr_time" +
                                            "FROM Ticket T JOIN Fare F ON T.fare_id = F.fare_id" +
                                            "WHERE T.ticket_id = ?";
        try{ 
            NewJourneyDepArrRM depArr = jdbcTemplate.queryForObject(GET_FARE_DEP_ARR_TIMES_QUERY, journeyDepArrTimesRowMapper, ticketId);
            
            return Optional.of(depArr);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public int getTimeConflictCountWithinJourneys(Journey newJourney) {
        final int journeyPlanId = newJourney.getJourneyPlanId();
        // journey_plan_id, dep_time, arr_time, dep_time, arr_time, dep_time, arr_time
        String CHECK_TIME_CONFLICT_COUNT_QUERY = "SELECT COUNT(*) AS conflict_count" +
                                                "FROM Journey j" +
                                                "INNER JOIN Ticket t ON j.ticket_id = t.ticket_id" +
                                                "INNER JOIN Fare f ON t.fare_id = f.fare_id"+
                                                "WHERE j.journey_plan_id = ? "+
                                                "AND ("+
                                                        "(f.departure_time <= ? AND f.estimated_arrival_time >= ?) "+
                                                        "OR (f.departure_time <= ? AND f.estimated_arrival_time >= ?) "+
                                                        "OR (? <= f.departure_time AND ? >= f.estimated_arrival_time)"+
                                                    ")";
        try {
            Optional<NewJourneyDepArrRM> depArr = getJourneyAffiliatedTicketDepArr(newJourney);
            Timestamp dep_time = depArr.get().getNewDepTime();
            Timestamp arr_time = depArr.get().getNewArrTime();
            int conflictCount = jdbcTemplate.queryForObject(CHECK_TIME_CONFLICT_COUNT_QUERY, Integer.class, journeyPlanId, 
                dep_time, arr_time,
                dep_time, arr_time,
                dep_time, arr_time);
            return conflictCount;
        } catch (EmptyResultDataAccessException e) {
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
