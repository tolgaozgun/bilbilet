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
public class SeatConfiguration {
	@Id
	@NotNull
	private int configuration_id;

	@NotBlank
	private String configuration_name;

}
