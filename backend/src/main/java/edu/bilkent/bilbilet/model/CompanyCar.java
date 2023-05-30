package edu.bilkent.bilbilet.model;

import java.math.BigDecimal;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyCar {
    @Id
    private int companyCarId;

    @NotNull
    private int carId;

    @NotNull
    private int companyId;

    @NotNull    
    private int addressId;
    
    private String photoUrl;
    
    private String websiteUrl;

    @NotNull
    private BigDecimal pricePerDay;
}
