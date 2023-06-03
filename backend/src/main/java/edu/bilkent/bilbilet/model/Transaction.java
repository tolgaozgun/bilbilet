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
    private int transactionId;

    @NotNull
    private TransactionType transactionType;

    @NotNull
    private BigDecimal transactionAmount;

    private Integer receiverId;

    private Integer senderId;
}
