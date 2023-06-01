package edu.bilkent.bilbilet.repository.rowmapper;

import java.math.BigDecimal;
import java.sql.Timestamp;

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

	private Timestamp startDate;

	private Timestamp endDate;

	private int userId; 
}
