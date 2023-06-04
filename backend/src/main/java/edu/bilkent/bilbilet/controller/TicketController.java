package edu.bilkent.bilbilet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.bilkent.bilbilet.exception.ExceptionLogger;
import edu.bilkent.bilbilet.model.Ticket;
import edu.bilkent.bilbilet.request.ticket.BuyTicketBalance;
import edu.bilkent.bilbilet.request.ticket.BuyTicketCC;
import edu.bilkent.bilbilet.request.ticket.CancelTicket;
import edu.bilkent.bilbilet.request.ticket.ReserveTicketBalance;
import edu.bilkent.bilbilet.request.ticket.ReserveTicketCC;
import edu.bilkent.bilbilet.response.RTicketView;
import edu.bilkent.bilbilet.response.RUserTicketView;
import edu.bilkent.bilbilet.response.Response;
import edu.bilkent.bilbilet.service.TicketService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/ticket")
public class TicketController {
    private final TicketService ticketService;

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping
    public ResponseEntity<Object> findAllTickets() {
        try {
            List<Ticket> tickets = ticketService.findAllTickets();
            return Response.create("ok", HttpStatus.OK, tickets);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("{ticketId}")
    public ResponseEntity<Object> findTicketByTicketId(@PathVariable int ticketId) {
        try {
            Optional<Ticket> tickets = ticketService.findTicketByTicketId(ticketId);
            return Response.create("ok", HttpStatus.OK, tickets);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }
    
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("detail/{ticketId}")
    public ResponseEntity<Object> findTicketDetailsByTicketId(@PathVariable int ticketId) {
        try {
            RUserTicketView tickets = ticketService.findTicketDetailsByTicketId(ticketId);
            return Response.create("ok", HttpStatus.OK, tickets);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }
    
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("fare/{fareId}")
    public ResponseEntity<Object> findTicketByFareId(@PathVariable int fareId) {
        try {
            List<RTicketView> tickets = ticketService.findTicketByFareId(fareId);
            return Response.create("ok", HttpStatus.OK, tickets);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }
    
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("user/{userId}")
    public ResponseEntity<Object> findTicketByUserId(@PathVariable int userId) {
        try {
            List<RUserTicketView> tickets = ticketService.findTicketByUserId(userId);
            return Response.create("ok", HttpStatus.OK, tickets);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "purchase/cc")
    public ResponseEntity<Object> buyTicketWithCC(@RequestBody BuyTicketCC ticketBuyingInfo) {
        try {
            List<RUserTicketView> tickets = ticketService.buyTicketWithCC(ticketBuyingInfo);
            return Response.create("Ticket is purchased successfuly", HttpStatus.OK, tickets);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }              
    }
    
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "purchase/balance")
    public ResponseEntity<Object> buyTicketWithBalance(@RequestBody BuyTicketBalance ticketBuyingInfo) {
        try {
            List<RUserTicketView> tickets = ticketService.buyTicketWithBalance(ticketBuyingInfo);
            return Response.create("Ticket is purchased successfuly", HttpStatus.OK, tickets);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }              
    }
    
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "cancel")
    public ResponseEntity<Object> cancelTicketOrReservation(@RequestBody CancelTicket cancelTicket) {
        try {
            Optional<Ticket> tickets = ticketService.cancelTicketOrReservation(cancelTicket);
            return Response.create("Ticket is cancelled successfuly", HttpStatus.OK, tickets);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }              
    }
    
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "reserve/cc")
    public ResponseEntity<Object> reserveTicketCC(@RequestBody ReserveTicketCC reserveTicket) {
        try {
            List<RUserTicketView> tickets = ticketService.reserveTicketCC(reserveTicket);
            return Response.create("Ticket is reserved successfuly", HttpStatus.OK, tickets);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }              
    }
    
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "reserve/balance")
    public ResponseEntity<Object> reserveTicketBalance(@RequestBody ReserveTicketBalance reserveTicket) {
        try {
            List<RUserTicketView> tickets = ticketService.reserveTicketBalance(reserveTicket);
            return Response.create("Ticket is reserved successfuly", HttpStatus.OK, tickets);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }              
    }
}
