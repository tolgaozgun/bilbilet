package edu.bilkent.bilbilet.controller;

import edu.bilkent.bilbilet.model.*;
import edu.bilkent.bilbilet.repository.rowmapper.CompanyCarRM;
import edu.bilkent.bilbilet.request.CompanyRegister;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.bilkent.bilbilet.exception.ExceptionLogger;
import edu.bilkent.bilbilet.request.TravelerRegister;
import edu.bilkent.bilbilet.request.UserLogin;
import edu.bilkent.bilbilet.response.RUserToken;
import edu.bilkent.bilbilet.response.Response;
import edu.bilkent.bilbilet.service.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/auth")
public class AccountController {

    private final AccountService accountService;

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "login")
    public ResponseEntity<Object> login(@Valid @RequestBody UserLogin userInfo) {
        try {
            RUserToken token = accountService.login(userInfo);
            return Response.create("login is successful", HttpStatus.OK, token);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "register/admin")
    public ResponseEntity<Object> register(@Valid @RequestBody User userInfo) {
        try {
            User user = accountService.addUser(userInfo);
            return Response.create("account is created", HttpStatus.OK, user);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "register/traveler")
    public ResponseEntity<Object> registerTraveler(@Valid @RequestBody TravelerRegister travelerInfo) {
        try {
            TravelerRegister savedInfo = accountService.addTraveler(travelerInfo);
            return Response.create("Traveler account registered", HttpStatus.OK, savedInfo.getUser());
        } catch (Exception e) {
            return Response.create(ExceptionLogger.error(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("company/{userId}")
    public ResponseEntity<Object> getCompany(@Valid @PathVariable("userId") int userId) {
        try {
            CompanyInfo companyInfo = accountService.getCompanyInfo(userId);
            return Response.create("User info gathered", HttpStatus.OK, companyInfo);
        } catch (Exception e) {
            return Response.create(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("traveler/{userId}")
    public ResponseEntity<Object> getTraveler(@Valid @PathVariable("userId") int userId) {
        try {
            TravelerInfo travelerInfo = accountService.getTravelerInfo(userId);
            return Response.create("User info gathered", HttpStatus.OK, travelerInfo);
        } catch (Exception e) {
            return Response.create(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "register/company")
    public ResponseEntity<Object> registerCompany(@Valid @RequestBody CompanyRegister companyRegister) {
        try {
            CompanyRegister savedInfo = accountService.addCompany(companyRegister);
            return Response.create("Company account registered", HttpStatus.OK, savedInfo.getCompany());
        } catch (Exception e) {
            return Response.create(ExceptionLogger.error(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("hello")
    public ResponseEntity<Object> refreshToken() {
        try {
            return Response.create("ok", HttpStatus.OK, "hello world");
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "test")
    public ResponseEntity<Object> testToken(@Valid @RequestBody String token) {
        try {
            return Response.create("ok", HttpStatus.OK, "hello world login good");
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}