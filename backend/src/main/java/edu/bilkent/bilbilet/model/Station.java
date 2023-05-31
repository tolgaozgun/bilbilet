package edu.bilkent.bilbilet.model;

import lombok.AllArgsConstructor;
import edu.bilkent.bilbilet.enums.StationType;
import jakarta.persistence.Id;
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

	@NotNull
	private StationType station_type;

	@NotNull
	private int address_id;

}
