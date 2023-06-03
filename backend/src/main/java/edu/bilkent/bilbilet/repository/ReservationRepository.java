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
import edu.bilkent.bilbilet.model.Ticket;
import edu.bilkent.bilbilet.repository.rowmapper.rm.TicketRowMapper;
import edu.bilkent.bilbilet.response.RTicketView;
import edu.bilkent.bilbilet.response.RUserTicketView;


@Qualifier("reservation_repo") // TO DO
@Repository
public class ReservationRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
} 
