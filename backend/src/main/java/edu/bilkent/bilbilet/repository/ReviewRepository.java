package edu.bilkent.bilbilet.repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import edu.bilkent.bilbilet.enums.CompanyType;
import edu.bilkent.bilbilet.model.Company;
import edu.bilkent.bilbilet.model.CompanyReview;
import edu.bilkent.bilbilet.model.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import edu.bilkent.bilbilet.enums.UserType;
import edu.bilkent.bilbilet.model.Traveler;
import edu.bilkent.bilbilet.model.TripReview;
import edu.bilkent.bilbilet.model.User;
import edu.bilkent.bilbilet.repository.rowmapper.rm.ReviewRowMapper;
import edu.bilkent.bilbilet.response.RCompanyReview;
import edu.bilkent.bilbilet.response.RReviewAvg;
import edu.bilkent.bilbilet.response.RTripReview;
import edu.bilkent.bilbilet.response.RTripReviewDetailedPrivate;
import edu.bilkent.bilbilet.response.RTripReviewDetailedPublic;

@Qualifier("review_repo")
@Repository
public class ReviewRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    

    public void save(CompanyReview cr) throws Exception {
        try {
            String sql = "INSERT INTO CompanyReview (review_id, company_id) " +
                     "VALUES (?, ?)";
        
            jdbcTemplate.update(
                sql,
                cr.getReviewId(), cr.getCompanyId());
        } catch (Exception e) {
            throw new Exception("Company review cannot be added " + e.getLocalizedMessage());
        }        
    }

    public void save(TripReview tr) throws Exception {
        try {
            String sql = "INSERT INTO TripReview (review_id, ticket_id) " +
                     "VALUES (?, ?)";
        
            jdbcTemplate.update(
                sql,
                tr.getReviewId(), tr.getTicketId());
        } catch (Exception e) {
            throw new Exception("Trip review cannot be added " + e.getLocalizedMessage());
        }        
    }

    public Review save(Review review) throws Exception {
        try {
            String sql = "INSERT INTO Review (comment, punctuality, cleanliness, customer_service, user_id) " +
                     "VALUES (?, ?, ?, ? ?)";
        
            KeyHolder keyHolder = new GeneratedKeyHolder();
            
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"review_id"});
                ps.setString(1, review.getComment());
                ps.setInt(2, review.getUserId());
                ps.setDouble(3, review.getPunctuality());
                ps.setDouble(4, review.getCleanliness());
                ps.setDouble(5, review.getCustomerService());
                return ps;
            }, keyHolder);
            
            int generatedId = keyHolder.getKey().intValue();
            review.setReviewId(generatedId);

            return review;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Review cannot be added " + e.getLocalizedMessage());
        }        
    }

    public List<RCompanyReview> findCompanyReviewByCompanyId(int companyId) {
        try {
            String sql = "SELECT * FROM CompanyReview cr INNER JOIN Review r ON r.review_id = cr.review_id WHERE cr.company_id = ?";

            return jdbcTemplate.query(sql, ReviewRowMapper.COMPANY_REVIEW_DETAILED_ROW_MAPPER, companyId);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<RTripReviewDetailedPublic> findTripReviewByCompanyId(int companyId) {
        try {
            String sql = "SELECT * FROM TripReview tr INNER JOIN Review r ON r.review_id = tr.review_id INNER JOIN "
                       + "Ticket t ON t.ticket_id = tr.ticket_id INNER JOIN Fare f ON f.fare_id = t.fare_id "
                       + "INNER JOIN Station s ON s.station_id = f.dep_stat_id AND s.station_id = f.arrive_stat_id "
                       + "INNER JOIN Company company ON company.compnay_id = f.company_id "
                       + "WHERE company_id = ?";

            return jdbcTemplate.query(sql, ReviewRowMapper.TRIP_REVIEW_DETAILED_PUBLIC_ROW_MAPPER, companyId);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<RTripReviewDetailedPrivate> findTripReviewByUserId(int userId) {
        try {
            String sql = "SELECT * FROM TripReview tr INNER JOIN Review r ON r.review_id = tr.review_id INNER JOIN "
                       + "Ticket t ON t.ticket_id = tr.ticket_id INNER JOIN Fare f ON f.fare_id = t.fare_id "
                       + "INNER JOIN Station s ON s.station_id = f.dep_stat_id AND s.station_id = f.arrive_stat_id "
                       + "INNER JOIN Company company ON company.compnay_id = f.company_id "
                       + "WHERE user_id = ?";

            return jdbcTemplate.query(sql, ReviewRowMapper.TRIP_REVIEW_DETAILED_PRIVATE_ROW_MAPPER, userId);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<RReviewAvg> findCompanyReviewAverage(int companyId) {
        try {
            String sql = "SELECT AVG(punctionality) AS avgPunct, AVG(cleanliness) AS avgClean, AVG(customer_service) AS avgCustSer "
                       + "FROM CompanyReview cr INNER JOIN Review r ON r.review_id = cr.review_id WHERE cr.company_id = ?";

            return jdbcTemplate.query(sql, ReviewRowMapper.REVIEW_AVG_ROW_MAPPER, companyId);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<RReviewAvg> findTripReviewAverageByCompany(int companyId) {
        try {
            String sql = "SELECT AVG(punctionality) AS avgPunct, AVG(cleanliness) AS avgClean, AVG(customer_service) AS avgCustSer "
                       + "FROM CompanyReview cr INNER JOIN Review r ON r.review_id = cr.review_id INNER JOIN "
                       + "Ticket t ON t.ticket_id = tr.ticket_id INNER JOIN Fare f ON f.fare_id = t.fare_id WHERE f.company_id = ?";

            return jdbcTemplate.query(sql, ReviewRowMapper.REVIEW_AVG_ROW_MAPPER, companyId);
        } catch (Exception e) {
            throw e;
        }
    }

    
}
