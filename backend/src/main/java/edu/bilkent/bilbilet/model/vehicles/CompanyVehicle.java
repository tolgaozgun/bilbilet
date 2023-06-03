package edu.bilkent.bilbilet.model.vehicles;

import lombok.AllArgsConstructor;
import edu.bilkent.bilbilet.enums.VehicleType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyVehicle {
	@Id
	@NotNull
	private int vehicleId;

	@NotNull
	private int capacity;

	@NotNull
	private VehicleType vehicleType;

	// Holds a reference to CompanyBus or CompanyPlane
	private int vehicleReferenceId;

	@NotNull
	private int seatConfigurationId;

	@NotNull
	private int companyId;

}
