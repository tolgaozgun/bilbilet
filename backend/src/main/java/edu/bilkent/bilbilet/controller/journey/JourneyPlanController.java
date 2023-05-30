package edu.bilkent.bilbilet.controller.journey;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.bilkent.bilbilet.exception.ExceptionLogger;
import edu.bilkent.bilbilet.request.journey.CreateJourneyPlan;
import edu.bilkent.bilbilet.response.Response;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/journeyPlan")
public class JourneyPlanController {
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createJourneyPlanForUser(@Valid @RequestBody CreateJourneyPlan journeyPlanInfo) {
        try {
            return Response.create("Traveler account registered", HttpStatus.OK);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.error(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(path = "user/{id}")
    public ResponseEntity<Object> getJourneyPlansForUser(@RequestParam("id") String id) {
        try {
            return Response.create("Traveler account registered", HttpStatus.OK);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.error(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
