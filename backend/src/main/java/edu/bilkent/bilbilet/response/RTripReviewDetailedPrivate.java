package edu.bilkent.bilbilet.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

import edu.bilkent.bilbilet.enums.TicketStatus;
import edu.bilkent.bilbilet.model.Review;
import edu.bilkent.bilbilet.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RTripReviewDetailedPrivate {
    private int ticketId;
    private Review review;
    private TicketStatus ticketStatus;
    private String depStationTitle;
    private String depStationAbbr;
    private String arrStationTitle;
    private String arrStationAbbr;
    private Timestamp depTime;
    private Timestamp arrTime;
    private BigDecimal price;
    private String companyTitle;
}

