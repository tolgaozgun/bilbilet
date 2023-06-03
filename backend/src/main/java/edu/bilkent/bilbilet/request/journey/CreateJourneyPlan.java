package edu.bilkent.bilbilet.request.journey;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateJourneyPlan {
    @NotBlank
	private String planTitle;

	@NotNull
	private int userId;
}
