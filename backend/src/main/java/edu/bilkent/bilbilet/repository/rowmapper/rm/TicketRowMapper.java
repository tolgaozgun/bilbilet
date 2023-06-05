package edu.bilkent.bilbilet.repository.rowmapper.rm;

import java.math.BigDecimal;

import org.springframework.jdbc.core.RowMapper;
import edu.bilkent.bilbilet.enums.SeatType;
import edu.bilkent.bilbilet.enums.TicketStatus;
import edu.bilkent.bilbilet.response.RTicketView;
import edu.bilkent.bilbilet.response.RUserTicketView;

public class TicketRowMapper {

    public static final RowMapper<RTicketView> DISPLAY_FARE_TICKETS_VIEW_ROW_MAPPER = (rs, rowNum) -> {
        RTicketView fareTicket = new RTicketView();
        fareTicket.setTicketId(rs.getInt("ticket_id"));
        fareTicket.setTicketStatus(TicketStatus.valueOf(rs.getString("ticket_status")));
        fareTicket.setSeatType(SeatType.valueOf(rs.getString("seat_type")));
        fareTicket.setSeatRow(rs.getInt("seat_row"));
        fareTicket.setSeatColumn(rs.getInt("seat_column"));
        fareTicket.setFareId(rs.getInt("fare_id"));
        fareTicket.setTotalPrice(rs.getDouble("total_price"));
        return fareTicket;
    };
    
    public static final RowMapper<Integer> COMPANY_USER_ID_ROW_MAPPER = (rs, rowNum) -> {
        int companyUserId = rs.getInt("user_id");
        return companyUserId;
    };

    public static final RowMapper<RUserTicketView> DISPLAY_USER_TICKETS_VIEW_ROW_MAPPER = (rs, rowNum) -> {
        RUserTicketView displayFareTicket = new RUserTicketView();
        displayFareTicket.setTicketId(rs.getInt("ticket_id"));
        displayFareTicket.setTicketStatus(TicketStatus.valueOf(rs.getString("ticket_status")));
        displayFareTicket.setSeatType(SeatType.valueOf(rs.getString("seat_type")));
        displayFareTicket.setSeatRow(rs.getInt("seat_row"));
        displayFareTicket.setSeatColumn(rs.getInt("seat_column"));
        displayFareTicket.setFareId(rs.getInt("fare_id"));
        displayFareTicket.setTravelerId(rs.getInt("traveler_id"));
        displayFareTicket.setTotalPrice(rs.getDouble("total_price"));
        displayFareTicket.setDepartureTime(rs.getTimestamp("departure_time"));
        displayFareTicket.setArrivalTime(rs.getTimestamp("arrival_time"));
        displayFareTicket.setCompanyTitle(rs.getString("company_title"));
        displayFareTicket.setDepStationTitle(rs.getString("dep_title"));
        displayFareTicket.setDepStationAbbr(rs.getString("dep_abbreviation"));
        displayFareTicket.setArrStationTitle(rs.getString("arr_title"));
        displayFareTicket.setArrStationAbbr(rs.getString("arr_abbreviation"));
        return displayFareTicket;
    };

    public static final RowMapper<BigDecimal> RESERVATION_FEE_ROW_MAPPER = (rs, rowNum) -> {
        return rs.getBigDecimal("reservation_fee");
    };
    
}
