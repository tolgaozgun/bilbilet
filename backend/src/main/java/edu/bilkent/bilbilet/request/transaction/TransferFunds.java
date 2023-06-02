package edu.bilkent.bilbilet.request.transaction;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class TransferFunds {

    @NotNull
    int senderId;

    @NotNull
    int receiverId;

    @NotNull
    BigDecimal amount;
}
