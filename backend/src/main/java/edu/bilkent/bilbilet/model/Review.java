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
	private int review_id;

	@NotBlank
	private String comment;

	@NotNull
	private int punctuality;

	@NotNull
	private int cleanliness;

	@NotNull
	private int customer_service;

	@NotNull
	private int user_id;

}
