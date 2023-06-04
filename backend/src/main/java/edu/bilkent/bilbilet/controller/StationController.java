package edu.bilkent.bilbilet.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.bilkent.bilbilet.enums.StationType;
import edu.bilkent.bilbilet.exception.ExceptionLogger;
import edu.bilkent.bilbilet.model.Station;
import edu.bilkent.bilbilet.request.AddStation;
import edu.bilkent.bilbilet.response.RStationAddress;
import edu.bilkent.bilbilet.response.Response;
import edu.bilkent.bilbilet.service.StationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/stations")
public class StationController {

    private final StationService stationService;

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping()
    public ResponseEntity<Object> getStations() {
        try {
            List<RStationAddress> stationList = stationService.getStations();
            return Response.create("Fetched all stations successfully.", HttpStatus.OK, stationList);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(path = "/plane")
    public ResponseEntity<Object> getPlaneStations() {
        try {
            List<RStationAddress> stationList = stationService.getStationsByType(StationType.AIRPORT);
            return Response.create("Fetched plane stations successfully.", HttpStatus.OK, stationList);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping(path = "/bus")
    public ResponseEntity<Object> getBusStations() {
        try {
            List<RStationAddress> stationList = stationService.getStationsByType(StationType.BUS_TERMINAL);
            return Response.create("Fetched bus stations successfully.", HttpStatus.OK, stationList);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "")
    public ResponseEntity<Object> addStation(@Valid @RequestBody AddStation stationInfo) {
        try {
            Station newStation = stationService.addStation(stationInfo);
            return Response.create("Station created successfully.", HttpStatus.OK, newStation);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}