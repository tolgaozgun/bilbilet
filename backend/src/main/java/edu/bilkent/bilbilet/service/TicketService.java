package edu.bilkent.bilbilet.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.bilkent.bilbilet.enums.TicketStatus;
import edu.bilkent.bilbilet.exception.ItemNotFoundException;
import edu.bilkent.bilbilet.model.Ticket;
import edu.bilkent.bilbilet.repository.TicketRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public boolean isTicketAvailable(int ticketId) throws Exception {
        try {
            Optional<Ticket> ticket = ticketRepository.findTicketBasedOnStatus(ticketId, TicketStatus.AVAILABLE);
            if (!ticket.isPresent()) {
                return false;
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Optional<Ticket> getTicketByTicketId(int ticketId) throws Exception, ItemNotFoundException {
        try {
            Optional<Ticket> ticket = ticketRepository.findTicket(ticketId);
            return ticket;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
