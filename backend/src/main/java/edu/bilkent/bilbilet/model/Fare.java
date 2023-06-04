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
	private Timestamp estimatedDepTime;

	@NotNull
	private Timestamp estimatedArrTime;

	@NotNull
	private BigDecimal price;
	
	@NotNull
	private BigDecimal premiumEconExtraPrice;
	
	@NotNull
	private BigDecimal businessExtraPrice;
	
	@NotNull
	private BigDecimal firstClassExtraPrice;

	@NotNull
	private BigDecimal reservationFee;

	@NotNull
	private int companyId;

	@NotNull
	private int vehicleId;

	@NotNull
	private int depStationId;

	@NotNull
	private int arrStationId;
}
