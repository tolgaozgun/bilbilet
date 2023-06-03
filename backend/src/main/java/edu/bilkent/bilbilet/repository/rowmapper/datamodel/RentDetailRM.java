package edu.bilkent.bilbilet.repository.rowmapper.datamodel;

import java.util.Date;

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
