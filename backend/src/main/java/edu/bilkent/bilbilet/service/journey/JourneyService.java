package edu.bilkent.bilbilet.service.journey;

import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import edu.bilkent.bilbilet.exception.InsertionFailedException;
import edu.bilkent.bilbilet.exception.ItemNotFoundException;
import edu.bilkent.bilbilet.model.Journey;
import edu.bilkent.bilbilet.repository.journey.JourneyRepository;
import edu.bilkent.bilbilet.request.journey.CreateJourney;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JourneyService {
    private final  JourneyRepository journeyRepository;    

    public Journey createJourney(CreateJourney createJourney) throws Exception, InsertionFailedException{
        Journey j = new Journey();
        j.setJourney_id(0);
        j.setJourney_title(createJourney.getJourney_title());
        j.setJourney_plan_id(createJourney.getJourney_plan_id());
        j.setFare_id(createJourney.getFare_id());
        try {
            Optional<Journey> newJourney = journeyRepository.createJourney(j);
            if (!newJourney.isPresent()) {
                throw new InsertionFailedException("Journey could not be created.");
            }
    
            return newJourney.get();
        } catch (Exception e) {
            throw e;
        }
    }

    public Journey getJourney(Long journeyId) throws Exception, EmptyResultDataAccessException {
        try {
            Optional<Journey> journey = journeyRepository.getJourney(journeyId);
            if (!journey.isPresent()) {
                throw new ItemNotFoundException("Journey details cannot be found.");
            }
    
            return journey.get();
        } catch (EmptyResultDataAccessException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }
}
