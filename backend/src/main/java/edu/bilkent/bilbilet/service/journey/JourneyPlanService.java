package edu.bilkent.bilbilet.service.journey;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.bilkent.bilbilet.model.JourneyPlan;
import edu.bilkent.bilbilet.repository.journey.JourneyPlanRepository;
import edu.bilkent.bilbilet.repository.journey.JourneyRepository;
import edu.bilkent.bilbilet.request.journey.CreateJourneyPlan;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JourneyPlanService {
    private final JourneyPlanRepository journeyPlanRepository;

    public JourneyPlan createJourneyPlan(CreateJourneyPlan journeyPlanDto) throws Exception {
        try {
            JourneyPlan jp = new JourneyPlan(journeyPlanDto);
            Optional<JourneyPlan> newJp = journeyPlanRepository.createJourneyPlan(jp);
            if (!newJp.isPresent()) {
                throw new Exception("Couldn't create the journey plan. Make sure to have each of the journey plans with a different title.");
            }
    
            return newJp.get();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public JourneyPlan deleteJourneyPlan(int jpId) throws Exception {
        try {
            Optional<JourneyPlan> deletedJp = journeyPlanRepository.deleteJourneyPlan(jpId);
            if (!deletedJp.isPresent()) {
                throw new Exception("Couldn't delete the journey plan. Make sure to have each of the journey plans with a different title.");
            }
    
            return deletedJp.get();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<JourneyPlan> getJourneyPlans(int userId) throws Exception {
        try {
            Optional<List<JourneyPlan>> newJp = journeyPlanRepository.getJourneyPlans(userId);
            if (!newJp.isPresent()) {
                throw new Exception("Couldn't get the journey plans.");
            }
    
            return newJp.get();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
