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
public class Station {
	@Id
	@NotNull
	private int station_id;

	@NotBlank
	private String station_type;

	@NotNull
	private int address_id;

}
