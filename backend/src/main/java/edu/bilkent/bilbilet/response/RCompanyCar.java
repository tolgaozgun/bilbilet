package edu.bilkent.bilbilet.response;

import java.math.BigDecimal;

import edu.bilkent.bilbilet.request.AddCompanyCar;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RCompanyCar {
    private int companyCarId;
    private int carId;
    private int companyId;
    private String country;
    private String city;
    private BigDecimal pricePerDay;

    public RCompanyCar(int ccId, AddCompanyCar cc) {
        this(ccId, cc.getCarId(), cc.getCompanyId(), cc.getCountry(), cc.getCity(), cc.getPricePerDay());
    }
}

