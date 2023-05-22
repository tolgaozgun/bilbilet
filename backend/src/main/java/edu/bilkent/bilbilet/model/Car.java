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
public class Car {
	@Id
	@NotNull
	private int car_id;

	@NotNull
	private boolean is_available;

	@NotNull
	private int capacity;

	@NotBlank
	private String gear;

	@NotNull
	private BigDecimal price_per_day;

	@NotBlank
	private String model;

	@NotBlank
	private String brand;

	@NotBlank
	private String category;

	@NotBlank
	private String fuel_type;

	@NotBlank
	private String photo_url;

	@NotBlank
	private String website_url;

	@NotNull
	private int company_id;

	@NotNull
	private int address_id;

}
