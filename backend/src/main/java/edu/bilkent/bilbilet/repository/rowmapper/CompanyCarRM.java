package edu.bilkent.bilbilet.repository.rowmapper;

import java.math.BigDecimal;

import edu.bilkent.bilbilet.model.Address;
import edu.bilkent.bilbilet.model.Car;
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

    private Address address;

    private BigDecimal pricePerDay;
}
