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
public class Address {
    @Id
    @NotNull
    private int address_id;

    @NotBlank
    private String country;

    @NotBlank
    private String city;

    @NotNull
    private int latitude;

    @NotNull
    private int longitude;
}
