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
public class Journey {
	@Id
	@NotNull
	private int journey_id;

	@NotBlank
	private String journey_title;

	@NotNull
	private int journey_plan_id;

	@NotNull
	private int fare_id;

}
