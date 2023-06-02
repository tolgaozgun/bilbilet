package edu.bilkent.bilbilet.request.transaction;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Withdraw {

    @NotNull
    int travelerId;
    @NotNull
    String ibanNumber;

    @NotNull
    int amount;
}
