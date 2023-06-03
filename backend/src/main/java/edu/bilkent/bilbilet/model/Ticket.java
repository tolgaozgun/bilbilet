package edu.bilkent.bilbilet.model;

import lombok.AllArgsConstructor;
import edu.bilkent.bilbilet.enums.SeatType;
import edu.bilkent.bilbilet.enums.TicketStatus;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket { //ticket(ticket id, ticket status, seat class, row, column, fare id)
	@Id
	@NotNull
	private int ticketId;

	@NotNull
	private TicketStatus ticketStatus;
	
	@NotNull
	private SeatType seatType;

	@NotNull
	private int seatRow;
	
	@NotNull
	private int seatColumn;

	@NotNull
	private int fareId;

	@NotNull
	private int travelerId;
}
