package edu.bilkent.bilbilet.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.bilkent.bilbilet.exception.ItemNotFoundException;
import edu.bilkent.bilbilet.exception.TicketNotAvailableException;
import edu.bilkent.bilbilet.model.Ticket;
import edu.bilkent.bilbilet.repository.AccountRepository;
import edu.bilkent.bilbilet.repository.TicketRepository;
import edu.bilkent.bilbilet.repository.fare.FareRepository;
import edu.bilkent.bilbilet.request.ticket.BuyTicketBalance;
import edu.bilkent.bilbilet.request.ticket.BuyTicketCC;
import edu.bilkent.bilbilet.request.ticket.CancelTicket;
import edu.bilkent.bilbilet.request.ticket.ReserveTicketBalance;
import edu.bilkent.bilbilet.request.ticket.ReserveTicketCC;
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

    public List<Ticket> findAllTickets() throws Exception {
        try {
            return ticketRepository.findAllTickets();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Optional<Ticket> findTicketByTicketId(int ticketId) throws Exception, ItemNotFoundException {
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
            if (!fareExist) {
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
    
    public List<RUserTicketView> buyTicketWithCC(BuyTicketCC buyTicket) throws Exception, ItemNotFoundException {
        try {
            buyTicket(buyTicket.getTravelerId(), buyTicket.getTicketId());

            // TO DO transactions

            return ticketRepository.findTicketDetailsByTicketId(buyTicket.getTicketId());
        } catch (ItemNotFoundException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<RUserTicketView> buyTicketWithBalance(BuyTicketBalance buyTicket) throws Exception, ItemNotFoundException {
        try {
            buyTicket(buyTicket.getTravelerId(), buyTicket.getTicketId());

            // TO DO transactions

            return ticketRepository.findTicketDetailsByTicketId(buyTicket.getTicketId());
        } catch (ItemNotFoundException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void buyTicket(int userId, int ticketId) throws Exception, ItemNotFoundException {
        try {
            // check if user exist
            boolean userExist = accountRepository.travelerExistByUserId(userId);
            if (!userExist) {
                throw new ItemNotFoundException("User does not exist!");
            }

            // check if seat is available
            boolean seatAvailable = ticketRepository.isTicketAvailable(ticketId);
            if (!seatAvailable) {
                throw new TicketNotAvailableException();
            }

            Optional<Ticket> tickets = ticketRepository.buyTicket(userId, ticketId);

            if (tickets.isEmpty()) {
                throw new ItemNotFoundException("Ticket does not exist");
            }

        } catch (ItemNotFoundException | TicketNotAvailableException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public Optional<Ticket> cancelTicketOrReservation(CancelTicket cancelTicket) throws Exception, ItemNotFoundException {
        try {
            // check if user exist
            boolean userExist = accountRepository.travelerExistByUserId(cancelTicket.getTicketId());
            if (!userExist) {
                throw new ItemNotFoundException("User does not exist!");
            }

            Optional<Ticket> tickets = ticketRepository.cancelTicketOrReservation(cancelTicket.getTicketId(), cancelTicket.getTicketId());

            if (tickets.isEmpty()) {
                throw new ItemNotFoundException("Ticket does not exist");
            }

            Optional<Ticket> ticket = ticketRepository.cancelTicketOrReservation(cancelTicket.getTravelerId(), cancelTicket.getTicketId());
            // TO DO add reservation repo

            // TO DO transaction

            return ticket;
        } catch (ItemNotFoundException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    private void reserveTicket(int userId, int ticketId) throws Exception, ItemNotFoundException {
        try {
            // check if user exist
            boolean userExist = accountRepository.travelerExistByUserId(userId);
            if (!userExist) {
                throw new ItemNotFoundException("User does not exist!");
            }

            // check if seat is available
            boolean seatAvailable = ticketRepository.isTicketAvailable(ticketId);
            if (!seatAvailable) {
                throw new TicketNotAvailableException();
            }

            Optional<Ticket> tickets = ticketRepository.cancelTicketOrReservation(userId, ticketId);

            if (tickets.isEmpty()) {
                throw new ItemNotFoundException("Ticket does not exist");
            }
        } catch (ItemNotFoundException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public List<RUserTicketView> reserveTicketCC(ReserveTicketCC reserveTicket) throws Exception, ItemNotFoundException {
        try {
            reserveTicket(reserveTicket.getTravelerId(), reserveTicket.getTicketId());

            // TO DO transaction // reservation fee comes from fare
            BigDecimal reservationFee = ticketRepository.findReservationFee(reserveTicket.getTicketId()).get(0);

            return ticketRepository.findTicketDetailsByTicketId(reserveTicket.getTicketId());
        } catch (ItemNotFoundException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public List<RUserTicketView> reserveTicketBalance(ReserveTicketBalance reserveTicket) throws Exception, ItemNotFoundException {
        try {
            reserveTicket(reserveTicket.getTravelerId(), reserveTicket.getTicketId());

            // TO DO transaction // reservation fee comes from fare
            BigDecimal reservationFee = ticketRepository.findReservationFee(reserveTicket.getTicketId()).get(0);

            return ticketRepository.findTicketDetailsByTicketId(reserveTicket.getTicketId());
        } catch (ItemNotFoundException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
