package edu.bilkent.bilbilet.model;

import lombok.AllArgsConstructor;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleSeatConfig {
	@Id
	@NotNull
	private int configId;

	@NotBlank
	private String configName;

    @NotBlank
    private String seatingArrangement; // "1, 2, 1", "3, 3" etc.

    @NotNull
    private int configRows;

    @NotNull
	private int configTotalColumns;

    @NotNull
	private int firstClassAfter;

    @NotNull
	private int businessClassAfter;

    @NotNull
	private int premiumEconomyClassAfter;
}
