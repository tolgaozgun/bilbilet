package edu.bilkent.bilbilet.request;

import java.math.BigDecimal;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCompanyCar {
    private int carId;
    private int companyId;
    private String country;
    private String city;
    private BigDecimal pricePerDay;
}
