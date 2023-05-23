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
	private int rent_id;

	@NotNull
	private Timestamp start_date;

	@NotNull
	private Timestamp end_date;

	@NotNull
	private int user_id;

	@NotNull
	private int car_id;

}
