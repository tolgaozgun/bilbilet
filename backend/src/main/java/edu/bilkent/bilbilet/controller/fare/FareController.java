package edu.bilkent.bilbilet.controller.fare;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.bilkent.bilbilet.enums.VehicleType;
import edu.bilkent.bilbilet.exception.CompanyNotFoundException;
import edu.bilkent.bilbilet.exception.InsertionFailedException;
import edu.bilkent.bilbilet.exception.NothingDeletedException;
import edu.bilkent.bilbilet.exception.UpdateFailedException;
import edu.bilkent.bilbilet.model.Fare;
import edu.bilkent.bilbilet.request.fare.CreateFare;
import edu.bilkent.bilbilet.response.Response;
import edu.bilkent.bilbilet.service.fare.FareService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/fare")
public class FareController {

    private final FareService fareService;

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/old")
    public ResponseEntity<Object> createFareOld(@Valid @RequestBody CreateFare fareInfo) {
        try {
            Fare fare = fareService.createFare(fareInfo);
            return Response.create("Successfully created fare.", HttpStatus.OK, fare);
        }
        catch (InsertionFailedException ife) {
            return Response.create(ife.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (CompanyNotFoundException cnfe) {
            return Response.create(cnfe.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return Response.create("Could not create fare. Perhaps an input was wrong?", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createFare(@Valid @RequestBody Fare fareInfo) {
        try {
            Fare fare = fareService.createFareNew(fareInfo);
            return Response.create("Successfully created fare.", HttpStatus.OK, fare);
        }
        catch (InsertionFailedException ife) {
            return Response.create(ife.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (CompanyNotFoundException cnfe) {
            return Response.create(cnfe.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return Response.create("Could not create fare. Perhaps an input was wrong?", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateFare(@Valid @RequestBody Fare fareDataToUpdate) {
        try {
            Fare fare = fareService.updateFare(fareDataToUpdate);
            return Response.create("Successfully updated fare.", HttpStatus.OK, fare);
        }
        catch (UpdateFailedException ufe) {
            return Response.create(ufe.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (CompanyNotFoundException cnfe) {
            return Response.create(cnfe.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return Response.create("Could not update fare. Perhaps an input was wrong?", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/{fareId}")
    public ResponseEntity<Object> updateFareById(@Valid @RequestBody Fare fareDataToUpdate, @PathVariable("fareId") int fareId) {
        try {
            Fare fare = fareService.updateFareById(fareDataToUpdate, fareId);
            return Response.create("Successfully updated fare.", HttpStatus.OK, fare);
        }
        catch (UpdateFailedException ufe) {
            return Response.create(ufe.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (CompanyNotFoundException cnfe) {
            return Response.create(cnfe.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return Response.create("Could not update fare. Perhaps an input was wrong?", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @DeleteMapping(path = "/{fareId}")
    public ResponseEntity<Object> deleteFareById(@PathVariable("fareId") int fareId) {
        try {
            boolean fareDeleted = fareService.deleteFareById(fareId);
            return Response.create("Successfully deleted fare with the ID \"" + fareId + "\".", HttpStatus.OK, fareDeleted);
        }
        catch (NothingDeletedException nde) {
            return Response.create(nde.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return Response.create("Could not delete fare.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(path = "/{companyId}")
    public ResponseEntity<Object> getFaresByCompanyId(@PathVariable("companyId") int companyId) {
        try {
            List<Fare> faresOfCompany = fareService.getFaresByCompanyId(companyId);
            return Response.create("Successfully fetched fares of company with the ID " + companyId + ".", HttpStatus.OK, faresOfCompany);
        }
        catch (CompanyNotFoundException cnfe) {
            return Response.create(cnfe.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return Response.create("Could not delete fare.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/plane")
    public ResponseEntity<Object> getPlaneFares(@RequestParam Map<String, Object> requestParams) {
        try {
            List<Fare> fares = fareService.getPlaneFaresByProperty(requestParams, VehicleType.PLANE);
            return Response.create("Successfully fetched plane fares.", HttpStatus.OK, fares);
        }
        catch (Exception e) {
            return Response.create(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/bus")
    public ResponseEntity<Object> getBusFares(@RequestParam Map<String, Object> requestParams) {
        try {
            List<Fare> fares = fareService.getPlaneFaresByProperty(requestParams, VehicleType.BUS);
            return Response.create("Successfully fetched bus fares.", HttpStatus.OK, fares);
        }
        catch (Exception e) {
            return Response.create(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }
}
