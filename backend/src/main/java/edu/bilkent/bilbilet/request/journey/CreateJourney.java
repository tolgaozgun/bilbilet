package edu.bilkent.bilbilet.request.journey;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateJourney {
    @NotBlank
	private String journeyTitle;

	@NotNull
	private int journeyPlanId;

	@NotNull
	private int ticketId;
}
