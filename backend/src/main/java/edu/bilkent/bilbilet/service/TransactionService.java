package edu.bilkent.bilbilet.service;

import edu.bilkent.bilbilet.enums.TransactionType;
import edu.bilkent.bilbilet.model.CreditCard;
import edu.bilkent.bilbilet.model.Transaction;
import edu.bilkent.bilbilet.model.Traveler;
import edu.bilkent.bilbilet.repository.AccountRepository;
import edu.bilkent.bilbilet.repository.TransactionRepository;
import edu.bilkent.bilbilet.request.transaction.*;
import edu.bilkent.bilbilet.utils.CreditCardChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public Transaction createTransaction(Transaction transaction) throws Exception {
        try {
            // Save the transaction
            Transaction createdTransaction = transactionRepository.save(transaction);

            if (createdTransaction == null){
                throw new Exception("Transaction could not be created");
            }

            return createdTransaction;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Transaction addFunds(AddFunds addFunds) {
        Transaction transaction = new Transaction();
        transaction.setTransaction_type(TransactionType.ADD_FUNDS);
        transaction.setTransaction_amount(BigDecimal.valueOf(addFunds.getBalance()));

        CreditCard creditCard = addFunds.getCreditCard();

        if (creditCard == null) {
            throw new IllegalArgumentException("Credit card cannot be null");
        }

        String creditCardNumber = creditCard.getCardNumber();

        if (creditCardNumber == null) {
            throw new IllegalArgumentException("Credit card number cannot be null");
        }

        int creditCardMonth = creditCard.getExpirationMonth();

        int creditCardYear = creditCard.getExpirationYear();

        if (creditCardMonth <= 0 || creditCardMonth > 12) {
            throw new IllegalArgumentException("Invalid month");
        }

        if (creditCardYear <= 0) {
            throw new IllegalArgumentException("Invalid year");
        }

        if (!CreditCardChecker.isValidExpiration(creditCardMonth, creditCardYear)) {
            throw new IllegalArgumentException("Invalid expiration date");
        }

        if (!CreditCardChecker.isValidCreditCardNumber(creditCardNumber)) {
            throw new IllegalArgumentException("Invalid credit card number");
        }

        int travelerId = addFunds.getTravelerId();

        Optional<Traveler> traveler = accountRepository.findTravelerByUserId(travelerId);

        if (traveler.isEmpty()) {
            throw new IllegalArgumentException("Invalid traveler id");
        }

        int balance = addFunds.getBalance();

        if (balance <= 0) {
            throw new IllegalArgumentException("Invalid balance");
        }

        // TODO: Add balance to the traveler.

        return transactionRepository.save(transaction);
    }

    public Transaction balancePayment(BalancePayment balancePayment) {
        Transaction transaction = new Transaction();
        transaction.setTransaction_type(TransactionType.BUY_TICKET_WITH_BALANCE);
        transaction.setTransaction_amount(BigDecimal.valueOf(balancePayment.getAmount()));
        // Set other necessary fields for balance payment transaction

        return transactionRepository.save(transaction);
    }

    public Transaction cardPayment(CardPayment cardPayment) {
        Transaction transaction = new Transaction();
        transaction.setTransaction_type(TransactionType.BUY_TICKET_WITH_CARD);
        transaction.setTransaction_amount(BigDecimal.valueOf(cardPayment.getAmount()));
        // Set other necessary fields for card payment transaction

        return transactionRepository.save(transaction);
    }

    public Transaction refund(Refund refund) {
        Transaction transaction = new Transaction();
        transaction.setTransaction_type(TransactionType.REFUND);
        transaction.setTransaction_amount(BigDecimal.valueOf(refund.getAmount()));
        // Set other necessary fields for refund transaction

        return transactionRepository.save(transaction);
    }

    public Transaction withdraw(Withdraw withdraw) {
        Transaction transaction = new Transaction();
        transaction.setTransaction_type(TransactionType.WITHDRAW);
        transaction.setTransaction_amount(BigDecimal.valueOf(withdraw.getAmount()));
        // Set other necessary fields for withdraw transaction

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsBySenderId(int senderId) {
        try {
            List<Transaction> transactions = transactionRepository.findTransactionsBySenderId(senderId);
            if (transactions == null){
                return Collections.emptyList();
            }
            return transactions;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Transaction> getTransactionsByReceiverId(int receiverId) {
        try {
            List<Transaction> transactions = transactionRepository.findTransactionsByReceiverId(receiverId);
            if (transactions == null){
                return Collections.emptyList();
            }
            return transactions;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Transaction> getAllTransactionsByUserId(int userId) {
        try {
            List<Transaction> transactions = transactionRepository.findAllTransactionsOfId(userId);

            if (transactions == null){
                return Collections.emptyList();
            }

            return transactions;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Transaction getTransactionById(int transactionId) {
        try {
            Optional<Transaction> transaction = transactionRepository.findTransactionById(transactionId);

            if (transaction.isEmpty()) {
                throw new RuntimeException("Transaction not found");
            }

            return transaction.get();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}

