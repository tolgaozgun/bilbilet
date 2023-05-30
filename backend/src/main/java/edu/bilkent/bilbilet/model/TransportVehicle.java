package edu.bilkent.bilbilet.model;

import lombok.AllArgsConstructor;
import edu.bilkent.bilbilet.enums.VehicleType;
import jakarta.persistence.Id;
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

	@NotNull
	private VehicleType vehicle_type;

	@NotNull
	private int seat_configuration_id;

	@NotNull
	private int company_id;

}
