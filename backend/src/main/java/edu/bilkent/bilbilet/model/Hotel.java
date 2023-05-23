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
	private int hotel_id;

	@NotBlank
	private String name;

	@NotNull
	private BigDecimal avg_price;

	@NotBlank
	private String telephone;

	@NotNull
	private BigDecimal rating;

	@NotBlank
	private String website_url;

	@NotBlank
	private String cover_photo_url;

	@NotBlank
	private String photo_url;

	@NotNull
	private int address_id;

}
