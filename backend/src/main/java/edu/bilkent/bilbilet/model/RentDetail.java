package edu.bilkent.bilbilet.model;

import lombok.AllArgsConstructor;
import java.sql.Timestamp;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentDetail {
	@Id
	@NotNull
	private int rentId;

	@NotNull
	private Timestamp startDate;

	@NotNull
	private Timestamp endDate;

	@NotNull
	private int userId;

	@NotNull
	private int companyCarId;
}
