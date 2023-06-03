package edu.bilkent.bilbilet.service;

import java.util.List;
import org.springframework.stereotype.Service;

import edu.bilkent.bilbilet.exception.CompanyNotFoundException;
import edu.bilkent.bilbilet.exception.ItemNotFoundException;
import edu.bilkent.bilbilet.exception.RentException;
import edu.bilkent.bilbilet.model.CompanyReview;
import edu.bilkent.bilbilet.model.Review;
import edu.bilkent.bilbilet.model.TripReview;
import edu.bilkent.bilbilet.repository.AccountRepository;
import edu.bilkent.bilbilet.repository.CompanyRepository;
import edu.bilkent.bilbilet.repository.ReviewRepository;
import edu.bilkent.bilbilet.request.AddCompanyReview;
import edu.bilkent.bilbilet.request.AddTripReview;
import edu.bilkent.bilbilet.response.RCompanyReview;
import edu.bilkent.bilbilet.response.RReviewAvg;
import edu.bilkent.bilbilet.response.RTripReview;
import edu.bilkent.bilbilet.response.RTripReviewDetailedPrivate;
import edu.bilkent.bilbilet.response.RTripReviewDetailedPublic;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final CompanyRepository companyRepository;
    private final AccountRepository accountRepository;
    private final ReviewRepository reviewRepository;

    public RCompanyReview addCompanyReview(AddCompanyReview cr) throws Exception {
        try {
            // check if user exist
            boolean userExist = accountRepository.travelerExistByUserId(cr.getUserId());
            if (!userExist) {
                throw new ItemNotFoundException("User does not exist!");
            }

            // check if company exists
            boolean companyExist = companyRepository.existByCompanyId(cr.getCompanyId());

            if (!companyExist) {
                throw new CompanyNotFoundException("Company not found");
            }

            // save review to reviews table
            Review newReview = reviewRepository.save(new Review(cr));
            reviewRepository.save(new CompanyReview(newReview.getReviewId(), cr.getCompanyId()));

            return new RCompanyReview(cr.getCompanyId(), newReview);
        } catch (ItemNotFoundException | CompanyNotFoundException ce) {
            throw ce;
        } catch (Exception e) {
            System.out.println("Company review cannot be added");
            e.printStackTrace();
            throw e;
        }
    }

    public RTripReview addTripReview(AddTripReview review) throws Exception {
        try {
            // check if user exist
            boolean userExist = accountRepository.travelerExistByUserId(review.getUserId());
            if (!userExist) {
                throw new ItemNotFoundException("User does not exist!");
            }

            // check if ticket exists
            // boolean ticketExists = companyRepository.existByCompanyId(cr.getCompanyId());
            boolean ticketExists = true;

            if (!ticketExists) {
                throw new ItemNotFoundException("Ticket not found");
            }

            // save review to reviews table
            Review newReview = reviewRepository.save(new Review(review));
            reviewRepository.save(new TripReview(newReview.getReviewId(), review.getTicketId()));

            return new RTripReview(review.getTicketId(),newReview);
        } catch (RentException ce) {
            throw ce;
        } catch (Exception e) {
            System.out.println("Trip review cannot be added");
            e.printStackTrace();
            throw e;
        }
    }

    public List<RCompanyReview> findCompanyReviewByCompanyId(int companyId) throws Exception {
        try {
            // check if company exists
            boolean companyExist = companyRepository.existByCompanyId(companyId);

            if (!companyExist) {
                throw new CompanyNotFoundException("Company not found");
            }

            return reviewRepository.findCompanyReviewByCompanyId(companyId); // TODO test
        } catch (CompanyNotFoundException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public List<RTripReviewDetailedPublic> findTripReviewByCompanyId(int companyId) throws Exception {
        try {
            // check if company exists
            boolean companyExist = companyRepository.existByCompanyId(companyId);

            if (!companyExist) {
                throw new CompanyNotFoundException("Company not found");
            }

            return reviewRepository.findTripReviewByCompanyId(companyId); // TODO test
        } catch (CompanyNotFoundException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public List<RTripReviewDetailedPrivate> findTripReviewByUserId(int userId) throws Exception {
        try {
            // check if user exist
            boolean userExist = accountRepository.travelerExistByUserId(userId);
            if (!userExist) {
                throw new ItemNotFoundException("User does not exist!");
            }

            return reviewRepository.findTripReviewByUserId(userId); // TODO test
        } catch (ItemNotFoundException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public List<RCompanyReview> findCompanyReviewByUserId(int userId) throws Exception {
        try {
            // check if user exist
            boolean userExist = accountRepository.travelerExistByUserId(userId);
            if (!userExist) {
                throw new ItemNotFoundException("User does not exist!");
            }

            return reviewRepository.findCompanyReviewByUserId(userId); // TODO test
        } catch (ItemNotFoundException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<RReviewAvg> findCompanyReviewAverage(int companyId) throws Exception {
        try {
            // check if company exists
            boolean companyExist = companyRepository.existByCompanyId(companyId);

            if (!companyExist) {
                throw new CompanyNotFoundException("Company not found");
            }

            return reviewRepository.findCompanyReviewAverage(companyId); // TODO test
        } catch (CompanyNotFoundException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<RReviewAvg> findTripReviewAverageByCompany(int companyId) throws Exception {
        try {
            // check if company exists
            boolean companyExist = companyRepository.existByCompanyId(companyId);

            if (!companyExist) {
                throw new CompanyNotFoundException("Company not found");
            }

            return reviewRepository.findTripReviewAverageByCompany(companyId); // TODO test
        } catch (CompanyNotFoundException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }    
}