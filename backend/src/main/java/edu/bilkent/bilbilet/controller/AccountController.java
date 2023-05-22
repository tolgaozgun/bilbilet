package edu.bilkent.bilbilet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.bilkent.bilbilet.response.Response;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/auth")
public class AccountController {
    

    // @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    // @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "login")
    // public ResponseEntity<Object> login(@Validated @RequestBody UserLogin login) {
    //     try {
    //         RLoginUser token = acco.login(userInfo);
    //         return Response.create("login is successful", HttpStatus.OK, token);
    //     } catch (Exception e) {
    //         return Response.create(ExceptionLogger.log(e), 499);
    //     }        
    // }

    // @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    // @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "register")
    // public ResponseEntity<Object> register(@Valid @RequestBody User userInfo) {
    //     try {
    //         User ids = accountService.addUser(userInfo);
    //         return Response.create("account is created", HttpStatus.OK, ids);
    //     } catch (Exception e) {
    //         return Response.create(ExceptionLogger.log(e), HttpStatus.CONFLICT);
    //     }        
    // }
    
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("hello")
    public ResponseEntity<Object> refreshToken() {
        try {
            return Response.create("ok", HttpStatus.OK, "hello world");
        } catch (Exception e) {
            return null;
        }        
    }
}