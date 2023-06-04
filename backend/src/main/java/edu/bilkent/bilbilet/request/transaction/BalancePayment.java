package edu.bilkent.bilbilet.request.transaction;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class BalancePayment {

    @NotNull
    int travelerId;

    @NotNull
    BigDecimal amount;
}
