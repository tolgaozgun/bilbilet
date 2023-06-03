package edu.bilkent.bilbilet.model;

import lombok.AllArgsConstructor;
import edu.bilkent.bilbilet.request.journey.CreateJourneyPlan;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JourneyPlan {
	public JourneyPlan(CreateJourneyPlan cJourneyPlan) {
		this.setJourneyPlanId(0);
		this.setPlanTitle(cJourneyPlan.getPlanTitle());
		this.setUserId(cJourneyPlan.getUserId());
	}

	@Id
	@NotNull
	private int journeyPlanId;

	@NotBlank
	private String planTitle;

	@NotNull
	private int userId;

}
