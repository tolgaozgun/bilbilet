package edu.bilkent.bilbilet.repository.rowmapper;

import java.math.BigDecimal;
import java.util.Date;

import edu.bilkent.bilbilet.model.Car;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentDetailRM {
    private CompanyCarRM companyCar;

    private int rentId;

	private Date startDate;

	private Date endDate;

	private int userId; 
}
