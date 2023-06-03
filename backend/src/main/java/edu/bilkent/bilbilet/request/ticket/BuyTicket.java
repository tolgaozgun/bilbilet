package edu.bilkent.bilbilet.request.ticket;

import jakarta.validation.constraints.NotNull;

public class BuyTicket {
    @NotNull
    private Long ticketId;

    @NotNull
    private Long userId;
}
