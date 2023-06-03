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
import edu.bilkent.bilbilet.repository.rowmapper.datamodel.CompanyCarRM;
import edu.bilkent.bilbilet.repository.rowmapper.datamodel.RentDetailRM;
import edu.bilkent.bilbilet.request.AddCompanyReview;
import edu.bilkent.bilbilet.request.AddTripReview;
import edu.bilkent.bilbilet.response.RCompanyReview;
import edu.bilkent.bilbilet.response.RReviewAvg;
import edu.bilkent.bilbilet.response.RTripReview;
import edu.bilkent.bilbilet.response.RTripReviewDetailedPrivate;
import edu.bilkent.bilbilet.response.RTripReviewDetailedPublic;
import edu.bilkent.bilbilet.response.Response;
import edu.bilkent.bilbilet.service.ReviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/review")
public class ReviewController {
    
    private final ReviewService reviewService;

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "company")
    public ResponseEntity<Object> addCompanyReview(@Valid @RequestBody AddCompanyReview review) {
        try {
            RCompanyReview newReview = reviewService.addCompanyReview(review);
            return Response.create("Company review is successfuly added", HttpStatus.OK, newReview);
        } catch (Exception e) {
            return Response.create(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }
    
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "trip")
    public ResponseEntity<Object> addTripReview(@Valid @RequestBody AddTripReview review) {
        try {
            RTripReview newReview = reviewService.addTripReview(review);
            return Response.create("Trip review is successfuly added", HttpStatus.OK, newReview);
        } catch (Exception e) {
            return Response.create(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("company/{companyId}")
    public ResponseEntity<Object> findCompanyReviewByCompanyId(@Valid @PathVariable("companyId") int companyId) {
        try {
            List<RCompanyReview> reviewList = reviewService.findCompanyReviewByCompanyId(companyId);
            return Response.create("ok", HttpStatus.OK, reviewList);
        } catch (Exception e) {
            return Response.create(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }
    
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("trip/{companyId}")
    public ResponseEntity<Object> findTripReviewByCompanyId(@Valid @PathVariable("companyId") int companyId) {
        try {
            List<RTripReviewDetailedPublic> reviewList = reviewService.findTripReviewByCompanyId(companyId);
            return Response.create("ok", HttpStatus.OK, reviewList);
        } catch (Exception e) {
            return Response.create(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }
    
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("trip/user/{userId}")
    public ResponseEntity<Object> findTripReviewByUserId(@Valid @PathVariable("userId") int userId) {
        try {
            List<RTripReviewDetailedPrivate> reviewList = reviewService.findTripReviewByUserId(userId);
            return Response.create("ok", HttpStatus.OK, reviewList);
        } catch (Exception e) {
            return Response.create(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("company/user/{userId}")
    public ResponseEntity<Object> findCompanyReviewByUserId(@Valid @PathVariable("userId") int userId) {
        try {
            List<RCompanyReview> reviewList = reviewService.findCompanyReviewByUserId(userId);
            return Response.create("ok", HttpStatus.OK, reviewList);
        } catch (Exception e) {
            return Response.create(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("company/{companyId}/avg")
    public ResponseEntity<Object> findCompanyReviewAverage(@Valid @PathVariable("companyId") int companyId) {
        try {
            List<RReviewAvg> reviewList = reviewService.findCompanyReviewAverage(companyId);
            return Response.create("ok", HttpStatus.OK, reviewList);
        } catch (Exception e) {
            return Response.create(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }
    
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("trip/{companyId}/avg")
    public ResponseEntity<Object> findTripReviewAverageByCompany(@Valid @PathVariable("companyId") int companyId) {
        try {
            List<RReviewAvg> reviewList = reviewService.findTripReviewAverageByCompany(companyId);
            return Response.create("ok", HttpStatus.OK, reviewList);
        } catch (Exception e) {
            return Response.create(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }
}
