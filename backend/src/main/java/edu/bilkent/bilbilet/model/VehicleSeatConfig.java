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
    private int configTotalRows; // i.e. 180

    @NotNull
	private int configTotalColumns; // i.e. 6

    @NotNull
	private int firstClassAfter;

    @NotNull
	private int businessClassAfter;

    @NotNull
	private int premiumEconomyClassAfter;
}
