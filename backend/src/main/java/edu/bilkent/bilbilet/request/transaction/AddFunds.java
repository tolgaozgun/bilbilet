package edu.bilkent.bilbilet.request.transaction;

import edu.bilkent.bilbilet.model.CreditCard;
import edu.bilkent.bilbilet.model.Traveler;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
public class AddFunds {

    @NotNull
    int travelerId;

    @NotNull
    CreditCard creditCard;

    @NotNull
    BigDecimal amount;


}
