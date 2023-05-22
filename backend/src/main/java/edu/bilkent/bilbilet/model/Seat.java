package edu.bilkent.bilbilet.model;

import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seat {
	@Id
	@NotNull
	private int seat_id;

	@NotBlank
	private String seat_class;

	@NotBlank
	private String seat_type;

	@NotNull
	private int row;

	@NotNull
	private int column;

	@NotNull
	private BigDecimal extra_price;

	@NotNull
	private int seat_configuration_id;

}
