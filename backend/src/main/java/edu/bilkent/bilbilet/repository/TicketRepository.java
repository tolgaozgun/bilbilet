package edu.bilkent.bilbilet.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.bilkent.bilbilet.enums.TicketStatus;
import edu.bilkent.bilbilet.model.Ticket;


@Qualifier("ticket_repo")
@Repository
public class TicketRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Ticket> ticketRowMapper = (rs, rowNum) -> {
        Ticket t = new Ticket();
        t.setFareId(rs.getInt("fare_id"));
        t.setSeatId(rs.getInt("seat_id"));
        t.setStatus(TicketStatus.valueOf(rs.getString("status")));
        t.setTicketId(rs.getInt("ticket_id"));
        t.setTravelerId(rs.getInt("traveler_id"));
        return t;
    };

    public Optional<Ticket> findTicket(int ticketId) {
        String sql = "SELECT * FROM Ticket WHERE ticket_id = ?";
        try {
            Optional<Ticket> ticket = Optional.of(jdbcTemplate.queryForObject(sql, ticketRowMapper, ticketId));
            return ticket;
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Optional<Ticket> findTicketBasedOnStatus(int ticketId, TicketStatus status) {
        String sql = "SELECT * FROM Ticket WHERE ticket_id = ? AND status = ?";
        try {
            Optional<Ticket> ticket = Optional.of(jdbcTemplate.queryForObject(sql, ticketRowMapper, ticketId, status.toString()));
            return ticket;
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

} 
