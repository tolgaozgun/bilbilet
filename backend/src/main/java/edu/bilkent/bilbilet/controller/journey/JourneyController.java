package edu.bilkent.bilbilet.controller.journey;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.bilkent.bilbilet.exception.ExceptionLogger;
import edu.bilkent.bilbilet.model.Journey;
import edu.bilkent.bilbilet.repository.journey.dto.JourneyDetailsRM;
import edu.bilkent.bilbilet.request.journey.CreateJourney;
import edu.bilkent.bilbilet.response.Response;
import edu.bilkent.bilbilet.service.journey.JourneyService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/journey")
public class JourneyController {

    private final JourneyService journeyService;

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createJourneyForUser(@Valid @RequestBody CreateJourney journeyInfo) {
        try {
            Journey journey = journeyService.createJourney(journeyInfo);
            return Response.create("Created the journey successfully", HttpStatus.OK, journey);
        } catch (Exception e) {
            return Response.create("Journey creation failed.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteJourney(@Valid @PathVariable("id") String id) {
        try {
            Journey journey = journeyService.deleteJourney(Integer.parseInt(id));
            return Response.create("Deleted journey successfully", HttpStatus.OK, journey);
        } catch (EmptyResultDataAccessException e) {
            return Response.create("Journey could not be found.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.error(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(path = "{id}")
    public ResponseEntity<Object> getJourneyDetailsByJourneyId(@Valid @PathVariable("id") String id) {
        try {
            Journey journey = journeyService.getJourney(Integer.parseInt(id));
            return Response.create("Accessed journey successfully", HttpStatus.OK, journey);
        } catch (EmptyResultDataAccessException e) {
            return Response.create("Journey could not be found.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.error(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(path = "/journeyPlan/{id}")
    public ResponseEntity<Object> getJourneysOfJourneyPlan(@Valid @PathVariable("id") String id) {
        try {
            List<JourneyDetailsRM> journey = journeyService.getJourneys(Integer.parseInt(id));
            return Response.create("Accessed journey successfully", HttpStatus.OK, journey);
        } catch (EmptyResultDataAccessException e) {
            return Response.create("Journey could not be found.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.error(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
