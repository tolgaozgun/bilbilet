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
public class Traveler {
    @Id
    @NotNull
    private int user_id;

    @NotBlank
    private String nationality;

    @NotBlank
    private String passport_number;

    @NotNull
    private BigDecimal balance;

    @NotBlank
    private String TCK;
}
