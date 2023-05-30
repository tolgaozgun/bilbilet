package edu.bilkent.bilbilet.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import edu.bilkent.bilbilet.enums.StationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class AddStation {

    @NotBlank
    private String title;

    @NotBlank
    private String city;

    @NotBlank
    private String abbreviation;

    @NotNull
    private StationType stationType;

}