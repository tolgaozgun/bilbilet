package edu.bilkent.bilbilet.model.vehicles;

import edu.bilkent.bilbilet.enums.VehicleType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyBus implements CompanyVehicleReference{

    @Id
    @NotNull
    private int busId;

    @NotNull
    private String plateNumber;

}