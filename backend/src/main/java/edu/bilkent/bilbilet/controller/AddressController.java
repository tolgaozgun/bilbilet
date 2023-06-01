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
import edu.bilkent.bilbilet.exception.ExceptionLogger;
import edu.bilkent.bilbilet.model.Address;
import edu.bilkent.bilbilet.request.AddAddress;
import edu.bilkent.bilbilet.response.Response;
import edu.bilkent.bilbilet.service.AddressService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/address")
public class AddressController {

    private final AddressService addressService;

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping()
    public ResponseEntity<Object> getAddresss() {
        try {
            List<Address> addressList = addressService.getAddress();
            return Response.create("Got address successfully", HttpStatus.OK, addressList);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "")
    public ResponseEntity<Object> addAddress(@Valid @RequestBody AddAddress addressInfo) {
        try {
            Address newAddress = addressService.addAddress(addressInfo);
            return Response.create("Address created successfully", HttpStatus.OK, newAddress);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}