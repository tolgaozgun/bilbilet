package edu.bilkent.bilbilet.repository.rowmapper.rm;

import org.springframework.jdbc.core.RowMapper;

import edu.bilkent.bilbilet.model.CompanyReview;
import edu.bilkent.bilbilet.model.Review;
import edu.bilkent.bilbilet.model.TripReview;
import edu.bilkent.bilbilet.response.RCompanyReview;
import edu.bilkent.bilbilet.response.RReviewAvg;
import edu.bilkent.bilbilet.response.RTripReview;
import edu.bilkent.bilbilet.response.RTripReviewDetailedPrivate;
import edu.bilkent.bilbilet.response.RTripReviewDetailedPublic;
import edu.bilkent.bilbilet.enums.TicketStatus;

public class ReviewRowMapper {
    public static final RowMapper<Review> REVIEW_ROW_MAPPER = (rs, rowNum) -> {
        Review review = new Review();
        review.setReviewId(rs.getInt("review_id"));
        review.setUserId(rs.getInt("user_id"));
        review.setComment(rs.getString("comment"));
        review.setPunctuality(rs.getDouble("punctuality"));
        review.setCleanliness(rs.getDouble("cleanliness"));
        review.setCustomerService(rs.getDouble("customer_service"));
        return review;
    };

    public static final RowMapper<TripReview> TRIP_REVIEW_ROW_MAPPER = (rs, rowNum) -> {
        TripReview tr = new TripReview();
        tr.setReviewId(rs.getInt("review_id"));
        tr.setTicketId(rs.getInt("ticket_id"));
        return tr;
    };

    public static final RowMapper<CompanyReview> COMPANY_REVIEW_ROW_MAPPER = (rs, rowNum) -> {
        CompanyReview cr = new CompanyReview();
        cr.setReviewId(rs.getInt("review_id"));
        cr.setCompanyId(rs.getInt("company_id"));
        return cr;
    };

    public static final RowMapper<RCompanyReview> COMPANY_REVIEW_DETAILED_ROW_MAPPER = (rs, rowNum) -> {
        RCompanyReview cr = new RCompanyReview();
        cr.setCompanyId(rs.getInt("company_id"));

        Review review = new Review();
        review.setReviewId(rs.getInt("review_id"));
        review.setUserId(rs.getInt("user_id"));
        review.setComment(rs.getString("comment"));
        review.setPunctuality(rs.getDouble("punctuality"));
        review.setCleanliness(rs.getDouble("cleanliness"));
        review.setCustomerService(rs.getDouble("customer_service"));

        cr.setReview(review);
        return cr;
    };

    public static final RowMapper<RTripReview> TRIP_REVIEW_DETAILED_ROW_MAPPER = (rs, rowNum) -> {
        RTripReview cr = new RTripReview();
        cr.setTicketId(rs.getInt("ticket_id"));

        Review review = new Review();
        review.setReviewId(rs.getInt("review_id"));
        review.setUserId(rs.getInt("user_id"));
        review.setComment(rs.getString("comment"));
        review.setPunctuality(rs.getDouble("punctuality"));
        review.setCleanliness(rs.getDouble("cleanliness"));
        review.setCustomerService(rs.getDouble("customer_service"));

        cr.setReview(review);
        return cr;
    };

    public static final RowMapper<RTripReviewDetailedPrivate> TRIP_REVIEW_DETAILED_PRIVATE_ROW_MAPPER = (rs, rowNum) -> {
        RTripReviewDetailedPrivate tripReview = new RTripReviewDetailedPrivate();
        tripReview.setTicketId(rs.getInt("ticket_id"));
    
        Review review = new Review();
        review.setReviewId(rs.getInt("review_id"));
        review.setUserId(rs.getInt("user_id"));
        review.setComment(rs.getString("comment"));
        review.setPunctuality(rs.getDouble("punctuality"));
        review.setCleanliness(rs.getDouble("cleanliness"));
        review.setCustomerService(rs.getDouble("customer_service"));
    
        tripReview.setReview(review);
        
        tripReview.setTicketStatus(TicketStatus.valueOf(rs.getString("ticket_status")));
        tripReview.setDepStationTitle(rs.getString("dep_station_title"));
        tripReview.setDepStationAbbr(rs.getString("dep_station_abbr"));
        tripReview.setArrStationTitle(rs.getString("arr_station_title"));
        tripReview.setArrStationAbbr(rs.getString("arr_station_abbr"));
        tripReview.setDepTime(rs.getTimestamp("dep_time"));
        tripReview.setArrTime(rs.getTimestamp("arr_time"));
        tripReview.setPrice(rs.getBigDecimal("price"));
        tripReview.setCompanyTitle(rs.getString("company_title"));
    
        return tripReview;
    };

    public static final RowMapper<RTripReviewDetailedPublic> TRIP_REVIEW_DETAILED_PUBLIC_ROW_MAPPER = (rs, rowNum) -> {
        RTripReviewDetailedPublic tripReview = new RTripReviewDetailedPublic();
        tripReview.setTicketId(rs.getInt("ticket_id"));
    
        Review review = new Review();
        review.setReviewId(rs.getInt("review_id"));
        review.setUserId(rs.getInt("user_id"));
        review.setComment(rs.getString("comment"));
        review.setPunctuality(rs.getDouble("punctuality"));
        review.setCleanliness(rs.getDouble("cleanliness"));
        review.setCustomerService(rs.getDouble("customer_service"));
    
        tripReview.setReview(review);

        tripReview.setDepStationTitle(rs.getString("dep_station_title"));
        tripReview.setDepStationAbbr(rs.getString("dep_station_abbr"));
        tripReview.setArrStationTitle(rs.getString("arr_station_title"));
        tripReview.setArrStationAbbr(rs.getString("arr_station_abbr"));
        tripReview.setDepTime(rs.getTimestamp("dep_time"));
        tripReview.setArrTime(rs.getTimestamp("arr_time"));
        tripReview.setCompanyTitle(rs.getString("company_title"));
    
        return tripReview;
    };
    

    public static final RowMapper<RReviewAvg> REVIEW_AVG_ROW_MAPPER = (rs, rowNum) -> {
        RReviewAvg avg = new RReviewAvg();
        avg.setAvgPunct(rs.getDouble("avgPunct"));
        avg.setAvgClean(rs.getDouble("avgClean"));
        avg.setAvgCustSer(rs.getDouble("avgCustSer"));
        
        return avg;
    };
}
