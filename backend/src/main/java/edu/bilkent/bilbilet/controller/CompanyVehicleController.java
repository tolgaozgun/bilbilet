package edu.bilkent.bilbilet.controller;


import edu.bilkent.bilbilet.exception.ExceptionLogger;
import edu.bilkent.bilbilet.model.vehicles.CompanyVehicle;
import edu.bilkent.bilbilet.request.vehicle.AddCompanyBus;
import edu.bilkent.bilbilet.request.vehicle.AddCompanyPlane;
import edu.bilkent.bilbilet.response.Response;
import edu.bilkent.bilbilet.service.CompanyVehicleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/company-vehicles")
public class CompanyVehicleController {

    private final CompanyVehicleService companyVehicleService;

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping("create/plane")
    public ResponseEntity<Object> createCompanyPlane(@Valid @RequestBody AddCompanyPlane addCompanyPlane) {
        try {
            CompanyVehicle createdVehicle = companyVehicleService.createCompanyPlane(addCompanyPlane);
            return Response.create("Created company vehicle successfully.", HttpStatus.OK, createdVehicle);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping("create/bus")
    public ResponseEntity<Object> createCompanyBus(@Valid @RequestBody AddCompanyBus companyBus) {
        try {
            CompanyVehicle createdVehicle = companyVehicleService.createCompanyBus(companyBus);
            return Response.create("Created company vehicle successfully.", HttpStatus.OK, createdVehicle);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("{vehicleId}")
    public ResponseEntity<Object> getCompanyVehicle(@PathVariable int vehicleId) {
        try {
            CompanyVehicle vehicle = companyVehicleService.getCompanyVehicleById(vehicleId);
            return Response.create("Found company vehicle successfully.", HttpStatus.OK, vehicle);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("company/{companyId}")
    public ResponseEntity<Object> getAllCompanyVehiclesByCompanyId(@PathVariable int companyId) {
        try {
            List<CompanyVehicle> vehicles = companyVehicleService.getAllCompanyVehicles(companyId);
            return Response.create("Found company vehicles successfully.", HttpStatus.OK, vehicles);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
