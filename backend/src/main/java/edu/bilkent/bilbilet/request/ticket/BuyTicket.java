package edu.bilkent.bilbilet.request.ticket;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyTicket {
    @NotNull
    private int ticketId;

    @NotNull
    private int userId;
}
