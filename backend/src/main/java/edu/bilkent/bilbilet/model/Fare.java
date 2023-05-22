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
	private int fare_id;

	@NotNull
	private Timestamp departure_time;

	@NotNull
	private Timestamp estimated_arrival_time;

	@NotNull
	private BigDecimal price;

	@NotNull
	private int company_id;

	@NotNull
	private int vehicle_id;

	@NotNull
	private int dep_stat_id;

	@NotNull
	private int arrive_stat_id;

}
