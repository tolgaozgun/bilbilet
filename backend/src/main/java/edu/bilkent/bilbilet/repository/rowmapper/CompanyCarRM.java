package edu.bilkent.bilbilet.repository.rowmapper;

import java.math.BigDecimal;

import edu.bilkent.bilbilet.model.Car;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyCarRM {
    private int companyCarId;

    private Car car;

    private int companyId;

    private int addressId;
    
    private String photoUrl;
    
    private String websiteUrl;

    private BigDecimal pricePerDay;
}
