package edu.bilkent.bilbilet.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import edu.bilkent.bilbilet.model.Hotel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class AddCompanyReview {
    @NotBlank
	private String comment;

	@NotNull
	private double punctuality;

	@NotNull
	private double cleanliness;

	@NotNull
	private double customerService;

	@NotNull
	private int userId;
	
    @NotNull
	private int companyId;
}
