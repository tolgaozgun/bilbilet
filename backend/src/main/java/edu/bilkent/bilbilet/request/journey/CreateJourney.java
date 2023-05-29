package edu.bilkent.bilbilet.request.journey;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateJourney {
    @NotBlank
	private String journey_title;

	@NotNull
	private int journey_plan_id;

	@NotNull
	private int fare_id;
}
