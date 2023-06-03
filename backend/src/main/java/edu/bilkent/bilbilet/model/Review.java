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
}
