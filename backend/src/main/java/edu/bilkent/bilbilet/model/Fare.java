package edu.bilkent.bilbilet.model;

import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.sql.Timestamp;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fare {
	@Id
	@NotNull
	private int fareId;

	@NotNull
	private Timestamp departureTime;

	@NotNull
	private Timestamp estimatedArrivalTime;

	@NotNull
	private BigDecimal price;

	@NotNull
	private int companyId;

	@NotNull
	private int vehicleId;

	@NotNull
	private int depStatId;

	@NotNull
	private int arriveStatId;

}
