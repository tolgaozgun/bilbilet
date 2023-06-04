package edu.bilkent.bilbilet.response;

import java.sql.Timestamp;

import edu.bilkent.bilbilet.enums.SeatType;
import edu.bilkent.bilbilet.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RUserTicketView {
    private int ticketId;
    private TicketStatus ticketStatus;
    private SeatType seatType;
    private int seatRow;
    private int seatColumn;
    private int fareId;
    private int travelerId;
    private double totalPrice;
    private Timestamp departureTime;
    private Timestamp arrivalTime;
    private String companyTitle;
    private String depStationTitle;
    private String depStationAbbr;
    private String arrStationTitle;
    private String arrStationAbbr;
}