package edu.bilkent.bilbilet.model;

import lombok.AllArgsConstructor;
import edu.bilkent.bilbilet.request.journey.CreateJourney;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Journey {
	public Journey(CreateJourney cj) {
        this.setJourneyId(0);
        this.setJourneyTitle(cj.getJourneyTitle());
        this.setJourneyPlanId(cj.getJourneyPlanId());
        this.setTicketId(cj.getTicketId());
	}

	@Id
	@NotNull
	private int journeyId;

	@NotBlank
	private String journeyTitle;

	@NotNull
	private int journeyPlanId;

	@NotNull
	private int ticketId;
}
