package edu.bilkent.bilbilet.request.transaction;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Refund {

    @NotNull
    int travelerId;
    @NotNull
    int amount;


}
