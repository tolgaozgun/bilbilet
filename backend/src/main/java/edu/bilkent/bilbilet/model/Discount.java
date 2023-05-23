package edu.bilkent.bilbilet.model;

import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discount {
	@Id
	@NotNull
	private int discount_id;

	@NotNull
	private BigDecimal discount_percentage;

	@NotNull
	private int fare_id;

}
