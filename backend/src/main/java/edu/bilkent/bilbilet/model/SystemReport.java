package edu.bilkent.bilbilet.model;

import lombok.AllArgsConstructor;
import edu.bilkent.bilbilet.enums.ReportType;
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

	@NotNull
	private ReportType report_type;

	@NotBlank
	private String report_month;

	@NotNull
	private int user_id;

}
