package edu.bilkent.bilbilet.repository.journey.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import edu.bilkent.bilbilet.enums.CompanyType;
import edu.bilkent.bilbilet.enums.StationType;
import edu.bilkent.bilbilet.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JourneyDetailsRM {
    int journeyId;
    String journeyTitle;
    TicketStatus ticketStatus;
    int seatId;
    Timestamp departureTime;
    Timestamp estimatedArrivalTime;
    BigDecimal price;
    String companyTitle;
    String address;
    CompanyType companyType;
    String contactInformation;
    String stationTitle;
    String abbreviation;
    StationType stationType;
}
