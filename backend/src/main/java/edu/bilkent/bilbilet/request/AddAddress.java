package edu.bilkent.bilbilet.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class AddAddress {

    @NotBlank
    private String city;

    @NotBlank
    private String country;

    @NotNull
    private BigDecimal longitude;

    @NotNull
    private BigDecimal latitude;
}