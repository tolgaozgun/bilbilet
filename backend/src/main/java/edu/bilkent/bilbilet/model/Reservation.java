package edu.bilkent.bilbilet.model;

import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.sql.Timestamp;

import edu.bilkent.bilbilet.enums.ReservationStatus;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
	@Id
	@NotNull
	private int reservationId;

	@NotNull
	private ReservationStatus reservationStatus;

	@NotNull
	private Timestamp createdAt;

	@NotNull
	private Timestamp reservedUntil;

	@NotNull
	private BigDecimal reservationFee;

	@NotNull
	private int seatId;

	@NotNull
	private int fareId;

	@NotNull
	private int travelerId;

}
