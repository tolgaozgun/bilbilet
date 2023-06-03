package edu.bilkent.bilbilet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.bilkent.bilbilet.enums.SeatType;
import edu.bilkent.bilbilet.enums.TicketStatus;
import edu.bilkent.bilbilet.exception.ItemNotFoundException;
import edu.bilkent.bilbilet.model.Ticket;
import edu.bilkent.bilbilet.repository.rowmapper.rm.TicketRowMapper;
import edu.bilkent.bilbilet.request.ticket.BuyTicket;
import edu.bilkent.bilbilet.request.ticket.ReserveTicket;
import edu.bilkent.bilbilet.response.RTicketView;
import edu.bilkent.bilbilet.response.RUserTicketView;


@Qualifier("ticket_repo")
@Repository
public class TicketRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Ticket> ticketRowMapper = (rs, rowNum) -> {
        Ticket t = new Ticket();
        t.setTicketId(rs.getInt("ticket_id"));
        t.setFareId(rs.getInt("fare_id"));
        t.setTicketStatus(TicketStatus.valueOf(rs.getString("ticket_status")));
        t.setSeatType(SeatType.valueOf(rs.getString("seat_type")));
        t.setSeatRow(rs.getInt("seat_row"));
        t.setSeatColumn(rs.getInt("seat_column"));        
        t.setTravelerId(rs.getInt("traveler_id"));
        return t;
    };

    public Optional<Ticket> findTicketByTicketId(int ticketId) {
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
    
    public List<RTicketView> findTicketByFareId(int fareId) {
        String sql = "SELECT * FROM DisplayFareTicketsView WHERE fare_id = ?";
        try {
            List<RTicketView> tickets = jdbcTemplate.query(sql, TicketRowMapper.DISPLAY_FARE_TICKETS_VIEW_ROW_MAPPER, fareId);
            
            return tickets;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public List<RUserTicketView> findTicketByUserId(int userId) {
        String sql = "SELECT * FROM DisplayUserTicketsView WHERE user_id = ?";
        try {
            List<RUserTicketView> ticket = jdbcTemplate.query(sql, TicketRowMapper.DISPLAY_USER_TICKETS_VIEW_ROW_MAPPER, userId);
            return ticket;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public boolean findTicketAndCheckStatus(int ticketId, TicketStatus status) throws ItemNotFoundException {
        String sql = "SELECT * FROM Ticket WHERE ticket_id = ?";
        try {
            Optional<Ticket> ticket = Optional.of(jdbcTemplate.queryForObject(sql, ticketRowMapper, ticketId));

            if (ticket.isPresent()) {
                return ticket.get().getTicketStatus().equals(status);
            } else {
                throw new ItemNotFoundException("Ticket id not found");
            }
        } catch (EmptyResultDataAccessException e) {
            throw new ItemNotFoundException("Ticket id not found");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Optional<Ticket> buyTicket(BuyTicket buyTicket) {
        String sql = "UPDATE Ticket SET ticket_status = ?, traveler_id = ? "
                    + "WHERE ticket_id = ?";
        try {
            jdbcTemplate.update(sql, TicketStatus.PURCHASED.toString(), buyTicket.getUserId(), buyTicket.getTicketId());
            Optional<Ticket> ticket = findTicketByTicketId(buyTicket.getTicketId());
            
            return ticket;
        } catch (Exception e) {
            throw e;
        }
    }

    public Optional<Ticket> cancelTicketOrReservation(BuyTicket buyTicket) {
        String sql = "UPDATE Ticket SET ticket_status = ? "
                    + "WHERE traveler_id = ? AND ticket_id";
        try {
            jdbcTemplate.update(sql, TicketStatus.AVAILABLE.toString(), buyTicket.getUserId(), buyTicket.getTicketId());
            Optional<Ticket> ticket = findTicketByTicketId(buyTicket.getTicketId());
            
            return ticket;
        } catch (Exception e) {
            throw e;
        }
    }

    public Optional<Ticket> reserveTicket(ReserveTicket reserveTicket) { // TO DO reserve repo
        String sql = "UPDATE Ticket SET ticket_status = ?, traveler_id = ? "
                    + "WHERE ticket_id = ?";
        try {
            jdbcTemplate.update(sql, TicketStatus.RESERVED.toString(), reserveTicket.getTravelerId(), reserveTicket.getTicketId());
            Optional<Ticket> ticket = findTicketByTicketId(reserveTicket.getTicketId());
            
            return ticket;
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean isTicketReserved(int ticketId) throws ItemNotFoundException {
        return findTicketAndCheckStatus(ticketId, TicketStatus.RESERVED);
    }

    public boolean isTicketAvailable(int ticketId) throws ItemNotFoundException {
        return findTicketAndCheckStatus(ticketId, TicketStatus.AVAILABLE);
    }

    public boolean isTicketPurchased(int ticketId) throws ItemNotFoundException {
        return findTicketAndCheckStatus(ticketId, TicketStatus.PURCHASED);
    }
} 
