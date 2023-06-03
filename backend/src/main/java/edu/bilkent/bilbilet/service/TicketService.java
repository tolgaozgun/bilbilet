package edu.bilkent.bilbilet.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.bilkent.bilbilet.enums.TicketStatus;
import edu.bilkent.bilbilet.exception.ItemNotFoundException;
import edu.bilkent.bilbilet.model.Ticket;
import edu.bilkent.bilbilet.repository.AccountRepository;
import edu.bilkent.bilbilet.repository.TicketRepository;
import edu.bilkent.bilbilet.repository.fare.FareRepository;
import edu.bilkent.bilbilet.response.RTicketView;
import edu.bilkent.bilbilet.response.RUserTicketView;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final FareRepository fareRepository;
    private final AccountRepository accountRepository;

    public boolean isTicketAvailable(int ticketId) throws Exception {
        try {
            return ticketRepository.isTicketAvailable(ticketId);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Optional<Ticket> getTicketByTicketId(int ticketId) throws Exception, ItemNotFoundException {
        try {
            Optional<Ticket> ticket = ticketRepository.findTicketByTicketId(ticketId);

            if (ticket.isEmpty()) {
                throw new ItemNotFoundException("Ticket id not found");
            }
            
            return ticket;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<RTicketView> findTicketByFareId(int fareId) throws Exception, ItemNotFoundException {
        try {
            // check if fare exists
            boolean fareExist = fareRepository.existsById(fareId);
            if (fareExist) {
                throw new ItemNotFoundException("Fare not found");
            }

            List<RTicketView> tickets = ticketRepository.findTicketByFareId(fareId);
            return tickets;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public List<RUserTicketView> findTicketByUserId(int userId) throws Exception, ItemNotFoundException {
        try {
            // check if user exist
            boolean userExist = accountRepository.travelerExistByUserId(userId);
            if (!userExist) {
                throw new ItemNotFoundException("User does not exist!");
            }

            List<RUserTicketView> tickets = ticketRepository.findTicketByUserId(userId);
            return tickets;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
