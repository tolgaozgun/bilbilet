package edu.bilkent.bilbilet.request.fare;

import edu.bilkent.bilbilet.model.Fare;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateFare {
    @NotBlank
	private String companyName;

	@NotNull
	private Fare fare;
}
