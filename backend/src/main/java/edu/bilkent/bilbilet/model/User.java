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
public class User {
	@Id
	@NotNull
	private int user_id;

	@NotBlank
	private String name;

	@NotBlank
	private String surname;

	@NotBlank
	private String email;

	@NotBlank
	private String telephone;

	@NotBlank
	private String password;

	@NotBlank
	private String user_type;

}
