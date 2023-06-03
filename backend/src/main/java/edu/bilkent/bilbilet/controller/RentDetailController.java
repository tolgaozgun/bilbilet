package edu.bilkent.bilbilet.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.bilkent.bilbilet.model.RentDetail;
import edu.bilkent.bilbilet.repository.rowmapper.CompanyCarRM;
import edu.bilkent.bilbilet.repository.rowmapper.RentDetailRM;
import edu.bilkent.bilbilet.response.Response;
import edu.bilkent.bilbilet.service.RentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/rentCar")
public class RentDetailController {
    
    private final RentService rentService;

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> rentCar(@Valid @RequestBody RentDetail rd) {
        try {
            RentDetail addedCar = rentService.rentCar(rd);
            return Response.create("Car rent is successful", HttpStatus.OK, addedCar);
        } catch (Exception e) {
            return Response.create(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("")
    public ResponseEntity<Object> findAvailableCars(@RequestParam Map<String, Object> requestParams) {
        try {
            List<CompanyCarRM> carList = rentService.findAvaliableCars(requestParams);
            return Response.create("ok", HttpStatus.OK, carList);
        } catch (Exception e) {
            return Response.create(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("{userId}")
    public ResponseEntity<Object> getUserRentList(@Valid @PathVariable("userId") int userId) {
        try {
            List<RentDetailRM> carList = rentService.getUserRentList(userId);
            return Response.create("ok", HttpStatus.OK, carList);
        } catch (Exception e) {
            return Response.create(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }
}
