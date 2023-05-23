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
public class TransportVehicle {
	@Id
	@NotNull
	private int vehicle_id;

	@NotBlank
	private String vehicle_type;

	@NotNull
	private int seat_configuration_id;

	@NotNull
	private int company_id;

}
