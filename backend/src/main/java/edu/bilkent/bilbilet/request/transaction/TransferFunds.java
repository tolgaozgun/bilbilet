package edu.bilkent.bilbilet.request.transaction;

import jakarta.validation.constraints.NotNull;

public class TransferFunds {

    @NotNull
    int senderId;

    @NotNull
    int receiverId;

    @NotNull
    int amount;
}
