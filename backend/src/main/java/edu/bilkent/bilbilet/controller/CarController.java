package edu.bilkent.bilbilet.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.bilkent.bilbilet.enums.FuelType;
import edu.bilkent.bilbilet.exception.ExceptionLogger;
import edu.bilkent.bilbilet.model.Car;
import edu.bilkent.bilbilet.model.CompanyCar;
import edu.bilkent.bilbilet.model.User;
import edu.bilkent.bilbilet.request.TravelerRegister;
import edu.bilkent.bilbilet.request.UserLogin;
import edu.bilkent.bilbilet.response.RUserToken;
import edu.bilkent.bilbilet.response.Response;
import edu.bilkent.bilbilet.service.AccountService;
import edu.bilkent.bilbilet.service.CarService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/car")
public class CarController {
    
    private final CarService carService;

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addCar(@Valid @RequestBody Car car) {
        try {
            Car addedCar = carService.addCar(car);
            return Response.create("New car is added sucessfuly", HttpStatus.OK, addedCar);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "company")
    public ResponseEntity<Object> addCompanyCar(@Valid @RequestBody CompanyCar companyCar) {
        try {
            CompanyCar addedCar = carService.addCompanyCar(companyCar);
            return Response.create("New car is added sucessfuly", HttpStatus.OK, addedCar);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("")
    public ResponseEntity<Object> getCars(@RequestParam Map<String, Object> requestParams) { // required true falan
        try {
            List<Car> carList = carService.getCarByProperties(requestParams);
            return Response.create("ok", HttpStatus.OK, carList);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("{companyId}")
    public ResponseEntity<Object> getCompanyCars(@Valid @PathVariable("companyId") int companyId) { // required true falan
        try {
            List<CompanyCar> companyCarList = carService.getAllCompanyCar(companyId);
            return Response.create("ok", HttpStatus.OK, companyCarList);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }
}