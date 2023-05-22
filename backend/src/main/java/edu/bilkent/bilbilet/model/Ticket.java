package edu.bilkent.bilbilet.model;

import lombok.AllArgsConstructor;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
	@Id
	@NotNull
	private int ticket_id;

	@NotBlank
	private String status;

	@NotNull
	private int seat_id;

	@NotNull
	private int fare_id;

	@NotNull
	private int traveler_id;

}
