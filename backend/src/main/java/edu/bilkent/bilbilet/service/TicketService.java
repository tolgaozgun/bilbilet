package edu.bilkent.bilbilet.service;

import org.springframework.stereotype.Service;

import edu.bilkent.bilbilet.model.Ticket;
import edu.bilkent.bilbilet.repository.TicketRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TicketService {
    private final TicketRepository ticketRepository;

}
