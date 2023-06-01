package edu.bilkent.bilbilet.service.journey;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import edu.bilkent.bilbilet.exception.InsertionFailedException;
import edu.bilkent.bilbilet.exception.ItemNotFoundException;
import edu.bilkent.bilbilet.exception.TicketConflictException;
import edu.bilkent.bilbilet.model.Journey;
import edu.bilkent.bilbilet.model.Ticket;
import edu.bilkent.bilbilet.repository.journey.JourneyRepository;
import edu.bilkent.bilbilet.request.journey.CreateJourney;
import edu.bilkent.bilbilet.service.TicketService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JourneyService {
    

    private final  JourneyRepository journeyRepository;   
    private final TicketService ticketService; 

    public Journey createJourney(CreateJourney createJourney) throws Exception, InsertionFailedException{
        Journey j = new Journey(createJourney);

        try {
            boolean isTicketAvailable = ticketService.isTicketAvailable(j.getTicketId());
            if (!isTicketAvailable) {
                throw new Exception("Ticket is not available");
            }

            // TODO: Check time conflict

            // Failed for some reason -_-
            Optional<Journey> newJourney = journeyRepository.createJourney(j);
            if (!newJourney.isPresent()) {
                throw new InsertionFailedException("Journey could not be created.");
            }
    
            return newJourney.get();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Journey getJourney(int journeyId) throws Exception, EmptyResultDataAccessException {
        try {
            Optional<Journey> journey = journeyRepository.getJourney(journeyId);
            if (!journey.isPresent()) {
                throw new ItemNotFoundException("Journey details cannot be found.");
            }
    
            return journey.get();
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // public boolean timeConflictExists(int ticketId, int journeyPlanId) {

    // }
}
