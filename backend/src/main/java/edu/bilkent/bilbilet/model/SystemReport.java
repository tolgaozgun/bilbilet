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
public class SystemReport {
	@Id
	@NotNull
	private int report_id;

	@NotBlank
	private String report_url;

	@NotBlank
	private String report_type;

	@NotBlank
	private String report_month;

	@NotNull
	private int user_id;

}
