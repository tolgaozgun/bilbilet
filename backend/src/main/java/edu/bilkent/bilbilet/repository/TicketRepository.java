package edu.bilkent.bilbilet.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Qualifier("account_repo")
@Repository
public class TicketRepository {
    
}
