package edu.bilkent.bilbilet.model;

import lombok.AllArgsConstructor;
import java.math.BigDecimal;

import edu.bilkent.bilbilet.enums.FuelType;
import edu.bilkent.bilbilet.enums.GearType;
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
	private int carId;

	// @NotNull
	// private boolean isAvailable;

	@NotNull
	private int capacity;

	@NotBlank
	private GearType gear;

	// @NotNull
	// private BigDecimal pricePerDay;

	@NotBlank
	private String model;

	@NotBlank
	private String brand;

	@NotBlank
	private CarCategoryType category;

	@NotNull
	private FuelType fuelType;

	// @NotBlank
	private String photoUrl;

	// @NotBlank
	private String websiteUrl;

	// @NotNull
	// private int companyId;

	// @NotNull
	// private int addressId;
}
