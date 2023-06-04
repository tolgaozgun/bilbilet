package edu.bilkent.bilbilet.request.ticket;

import java.math.BigDecimal;

import edu.bilkent.bilbilet.model.CreditCard;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelTicket {
    @NotNull
    private int ticketId;

    @NotNull
    int travelerId;
}
