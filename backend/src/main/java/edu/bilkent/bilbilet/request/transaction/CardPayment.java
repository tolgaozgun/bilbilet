package edu.bilkent.bilbilet.request.transaction;

import edu.bilkent.bilbilet.model.CreditCard;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CardPayment {

    @NotNull
    int travelerId;

    @NotNull
    CreditCard creditCard;

    @NotNull
    BigDecimal amount;


}
