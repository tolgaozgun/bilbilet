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
public class JourneyPlan {
	@Id
	@NotNull
	private int journey_plan_id;

	@NotBlank
	private String plan_title;

	@NotNull
	private int traveler_id;

}
