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

import edu.bilkent.bilbilet.model.Car;
import edu.bilkent.bilbilet.model.CarBrand;
import edu.bilkent.bilbilet.repository.rowmapper.CompanyCarRM;
import edu.bilkent.bilbilet.request.AddCompanyCar;
import edu.bilkent.bilbilet.response.Response;
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
            return Response.create(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "company")
    public ResponseEntity<Object> addCompanyCar(@Valid @RequestBody AddCompanyCar companyCar) {
        try {
            int addCompanyCarId = carService.addCompanyCar(companyCar);
            return Response.create("New car is added sucessfuly", HttpStatus.OK, addCompanyCarId);
        } catch (Exception e) {
            return Response.create(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("")
    public ResponseEntity<Object> getCars(@RequestParam Map<String, Object> requestParams) {
        try {
            List<Car> carList = carService.getCarByProperties(requestParams);
            return Response.create("ok", HttpStatus.OK, carList);
        } catch (Exception e) {
            return Response.create(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("company/{companyId}")
    public ResponseEntity<Object> getCompanyCars(@Valid @PathVariable("companyId") int companyId) {
        try {
            List<CompanyCarRM> companyCarList = carService.findAllCompanyCar(companyId);
            return Response.create("ok", HttpStatus.OK, companyCarList);
        } catch (Exception e) {
            return Response.create(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("{companyCarId}")
    public ResponseEntity<Object> getCompanyCarById(@Valid @PathVariable("companyCarId") int companyCarId) {
        try {
            List<CompanyCarRM> companyCarList = carService.findAllCompanyCar(companyCarId);
            return Response.create("ok", HttpStatus.OK, companyCarList);
        } catch (Exception e) {
            return Response.create(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("brand")
    public ResponseEntity<Object> getBrands() {
        try {
            List<CarBrand> brandList = carService.findAllBrand();
            return Response.create("ok", HttpStatus.OK, brandList);
        } catch (Exception e) {
            return Response.create(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }
}