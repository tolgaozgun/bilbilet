package edu.bilkent.bilbilet.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard {

    @NotNull
    String cardNumber;

    @NotNull
    String cardHolderName;

    @NotNull
    int expirationMonth;

    @NotNull
    int expirationYear;

    @NotNull
    int cvv;
}
