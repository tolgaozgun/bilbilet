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

            int count = journeyRepository.getTimeConflictCountWithinJourneys(j);
            if (count > 1) {
                throw new TicketConflictException("Couldn't create the journey because it would result in a time conflict within your journey plan.");
            }

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

    // public List<Journey> getJourneys(int journeyPlanId) {
    //     try {
    //         Optional<Journey> journey = journeyRepository.getJourneys(journeyPlanId);
    //         if (!journey.isPresent()) {
    //             throw new ItemNotFoundException("Journey details cannot be found.");
    //         }
    
    //         return journey.get();
    //     } catch (EmptyResultDataAccessException e) {
    //         e.printStackTrace();
    //         throw e;
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         throw e;
    //     }
    // }

    public Journey deleteJourney(int journeyId) {
        // TODO:
        return new Journey();
    }
}
