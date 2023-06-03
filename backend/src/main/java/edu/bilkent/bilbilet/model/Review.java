package edu.bilkent.bilbilet.model;

import lombok.AllArgsConstructor;
import edu.bilkent.bilbilet.request.AddCompanyReview;
import edu.bilkent.bilbilet.request.AddTripReview;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
	@Id
	@NotNull
	private int reviewId;

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

	public Review(AddCompanyReview addReview) {
		this(0, addReview.getComment(), addReview.getPunctuality(),
			addReview.getCleanliness(), addReview.getCustomerService(), addReview.getUserId());
	}

	public Review(AddTripReview addReview) {
		this(0, addReview.getComment(), addReview.getPunctuality(),
			addReview.getCleanliness(), addReview.getCustomerService(), addReview.getUserId());
	}
}
