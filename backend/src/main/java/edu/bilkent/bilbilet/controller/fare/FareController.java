package edu.bilkent.bilbilet.controller.fare;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.bilkent.bilbilet.model.Fare;
import edu.bilkent.bilbilet.request.fare.CreateFare;
import edu.bilkent.bilbilet.response.Response;
import edu.bilkent.bilbilet.service.fare.IFareService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/fare")
public class FareController {

    private final IFareService fareService;

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createFare(@Valid @RequestBody CreateFare fareInfo) {
        try {
            Fare fare = fareService.createFare(fareInfo);
            return Response.create("Successfully created fare.", HttpStatus.OK, fare);
        }
        catch (Exception e) {
            return Response.create("Could not create fare. Perhaps an input was wrong?", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
