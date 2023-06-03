package edu.bilkent.bilbilet.model;

import lombok.AllArgsConstructor;
import edu.bilkent.bilbilet.enums.TicketStatus;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
	@Id
	@NotNull
	private int ticketId;

	@NotNull
	private TicketStatus status;

	@NotNull
	private int seatId;

	@NotNull
	private int fareId;

	@NotNull
	private int travelerId;

}
