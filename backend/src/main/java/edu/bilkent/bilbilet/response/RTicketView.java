package edu.bilkent.bilbilet.response;

import edu.bilkent.bilbilet.enums.SeatType;
import edu.bilkent.bilbilet.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RTicketView {
    private int ticketId;
    private TicketStatus ticketStatus;
    private SeatType seatType;
    private int seatRow;
    private int seatColumn;
    private int fareId;
    private double totalPrice;
}

