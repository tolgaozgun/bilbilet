package edu.bilkent.bilbilet.model;

import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
	@Id
	@NotNull
	private int hotelId;

	@NotBlank
	private String name;

	@NotNull
	private BigDecimal avgPrice;

	@NotBlank
	private String telephone;

	@NotNull
	private BigDecimal rating;

	@NotBlank
	private String websiteUrl;

	@NotBlank
	private String coverPhotoUrl;

	@NotBlank
	private String photoUrl;

	@NotNull
	private int addressId;

}
