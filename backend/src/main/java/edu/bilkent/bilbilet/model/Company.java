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
public class Company {
    @Id
    private int company_id;

    @NotBlank
    private String company_title;

    @NotBlank
    private String address;

    @NotBlank
    private String type;

    @NotBlank
    private String contact_information;

    @NotBlank
    private String business_registration;

    @NotNull
    private int balance;

    @NotNull
    private int user_id;
}
