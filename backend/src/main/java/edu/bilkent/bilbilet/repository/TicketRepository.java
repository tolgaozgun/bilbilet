package edu.bilkent.bilbilet.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


@Qualifier("ticket_repo")
@Repository
public class TicketRepository {
    
}
