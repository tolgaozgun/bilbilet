package edu.bilkent.bilbilet.model;

import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.sql.Timestamp;

import edu.bilkent.bilbilet.enums.ReservationStatus;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
	@Id
	@NotNull
	private int reservation_id;

	@NotNull
	private ReservationStatus reservation_status;

	@NotNull
	private Timestamp created_at;

	@NotNull
	private Timestamp reserved_until;

	@NotNull
	private BigDecimal reservation_fee;

	@NotNull
	private int seat_id;

	@NotNull
	private int fare_id;

	@NotNull
	private int traveler_id;

}
