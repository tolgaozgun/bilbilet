package edu.bilkent.bilbilet.model;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

import edu.bilkent.bilbilet.enums.TransactionType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @NotNull
    private int transaction_id;

    @NotNull
    private TransactionType transaction_type;

    @NotNull
    private BigDecimal transaction_amount;

    private Integer receiver_id;

    private Integer sender_id;
}
