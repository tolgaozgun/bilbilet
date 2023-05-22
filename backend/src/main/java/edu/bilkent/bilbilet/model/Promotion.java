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
public class Promotion {
    @Id
    @NotBlank
    private String promotion_code;

    @NotNull
    private int promotion_percentage;

    @NotNull
    private int user_id;

    @NotNull
    private int company_id;
}
