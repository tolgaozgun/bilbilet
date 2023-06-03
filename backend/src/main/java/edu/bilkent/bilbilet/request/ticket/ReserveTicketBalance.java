package edu.bilkent.bilbilet.request.ticket;

import java.math.BigDecimal;
import java.sql.Timestamp;

import edu.bilkent.bilbilet.model.CreditCard;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReserveTicketBalance {
	@NotNull
	private Timestamp reservedUntil;

	@NotNull
	private BigDecimal reservationFee;

	@NotNull
	private int ticketId;

	@NotNull
	private int travelerId;

    @NotNull
    BigDecimal amount;
}
