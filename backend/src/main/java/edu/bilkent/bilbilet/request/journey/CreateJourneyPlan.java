package edu.bilkent.bilbilet.request.journey;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateJourneyPlan {
    @NotBlank
	private String plan_title;

	@NotNull
	private int user_id;
}
