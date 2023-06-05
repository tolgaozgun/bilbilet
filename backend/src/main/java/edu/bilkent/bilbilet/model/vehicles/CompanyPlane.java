package edu.bilkent.bilbilet.model.vehicles;


import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyPlane implements CompanyVehicleReference {

    @Id
    @NotNull
    private int planeId;

    @NotNull
    private String tailNumber;

}