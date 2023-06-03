package edu.bilkent.bilbilet.service;

import edu.bilkent.bilbilet.enums.TransactionType;
import edu.bilkent.bilbilet.model.CreditCard;
import edu.bilkent.bilbilet.model.Transaction;
import edu.bilkent.bilbilet.model.Traveler;
import edu.bilkent.bilbilet.repository.AccountRepository;
import edu.bilkent.bilbilet.repository.TransactionRepository;
import edu.bilkent.bilbilet.request.transaction.*;
import edu.bilkent.bilbilet.utils.CreditCardChecker;
import edu.bilkent.bilbilet.utils.IbanChecker;
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

    public Transaction addFunds(AddFunds addFunds) throws Exception {
        try {
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

            // if (!CreditCardChecker.isValidExpiration(creditCardMonth, creditCardYear)) {
            //     throw new IllegalArgumentException("Invalid expiration date");
            // }

            // if (!CreditCardChecker.isValidCreditCardNumber(creditCardNumber)) {
            //     throw new IllegalArgumentException("Invalid credit card number");
            // }

            int travelerId = addFunds.getTravelerId();

            Optional<Traveler> traveler = accountRepository.findTravelerByUserId(travelerId);

            if (traveler.isEmpty()) {
                throw new IllegalArgumentException("Invalid traveler id");
            }

            BigDecimal amount = addFunds.getAmount();

            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Invalid amount");
            }

            accountRepository.incrementTravelerBalance(travelerId, amount);

            Transaction transaction = new Transaction();
            transaction.setTransactionType(TransactionType.ADD_FUNDS);
            transaction.setTransactionAmount(amount);
            transaction.setSenderId(null);
            transaction.setReceiverId(travelerId);
            return transactionRepository.save(transaction);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    public Transaction balancePayment(BalancePayment balancePayment) throws Exception{
        try {
            int travelerId = balancePayment.getTravelerId();

            Optional<Traveler> traveler = accountRepository.findTravelerByUserId(travelerId);

            if (traveler.isEmpty()) {
                throw new IllegalArgumentException("Invalid traveler id");
            }

            BigDecimal amount = balancePayment.getAmount();

            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Invalid amount");
            }

            accountRepository.decrementTravelerBalance(travelerId, amount);
            // TODO: Ticket purchase

            Transaction transaction = new Transaction();
            transaction.setTransactionType(TransactionType.BUY_TICKET_WITH_BALANCE);
            transaction.setTransactionAmount(amount);
            transaction.setSenderId(travelerId);
            transaction.setReceiverId(null);
            return transactionRepository.save(transaction);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    public Transaction transferFunds(TransferFunds transferFunds) throws Exception{
        try {
            int senderId = transferFunds.getSenderId();
            int receiverId = transferFunds.getReceiverId();

            Optional<Traveler> optionalSender = accountRepository.findTravelerByUserId(senderId);
            Optional<Traveler> optionalReceiver = accountRepository.findTravelerByUserId(receiverId);

            if (optionalSender.isEmpty()) {
                throw new IllegalArgumentException("Invalid traveler id for sender");
            }

            if (optionalReceiver.isEmpty()) {
                throw new IllegalArgumentException("Invalid traveler id for receiver");
            }

            Traveler sender = optionalSender.get();
            Traveler receiver = optionalReceiver.get();

            BigDecimal amount = transferFunds.getAmount();

            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Amount cannot be lower than 0!");
            }

            if (sender.getBalance().compareTo(amount) < 0) {
                throw new IllegalArgumentException("Insufficient funds!");
            }

            accountRepository.decrementTravelerBalance(senderId, amount);
            accountRepository.incrementTravelerBalance(receiverId, amount);

            Transaction transaction = new Transaction();
            transaction.setTransactionType(TransactionType.TRANSFER);
            transaction.setTransactionAmount(amount);
            transaction.setSenderId(senderId);
            transaction.setReceiverId(receiverId);
            return transactionRepository.save(transaction);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    public Transaction cardPayment(CardPayment cardPayment) throws Exception {
        try {
            CreditCard creditCard = cardPayment.getCreditCard();

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

            // if (!CreditCardChecker.isValidExpiration(creditCardMonth, creditCardYear)) {
            //     throw new IllegalArgumentException("Invalid expiration date");
            // }

            // if (!CreditCardChecker.isValidCreditCardNumber(creditCardNumber)) {
            //     throw new IllegalArgumentException("Invalid credit card number");
            // }

            int travelerId = cardPayment.getTravelerId();

            Optional<Traveler> traveler = accountRepository.findTravelerByUserId(travelerId);

            if (traveler.isEmpty()) {
                throw new IllegalArgumentException("Invalid traveler id");
            }

            BigDecimal amount = cardPayment.getAmount();

            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Invalid amount");
            }

            // TODO: Trip purchase


            Transaction transaction = new Transaction();
            transaction.setTransactionType(TransactionType.BUY_TICKET_WITH_CARD);
            transaction.setTransactionAmount(amount);
            transaction.setSenderId(travelerId);
            transaction.setReceiverId(null);
            return transactionRepository.save(transaction);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Transaction refund(Refund refund) throws Exception {
        try {
            int travelerId = refund.getTravelerId();

            Optional<Traveler> traveler = accountRepository.findTravelerByUserId(travelerId);

            if (traveler.isEmpty()) {
                throw new IllegalArgumentException("Invalid traveler id");
            }

            BigDecimal amount = refund.getAmount();

            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Invalid amount");
            }

            accountRepository.incrementTravelerBalance(travelerId, amount);

            Transaction transaction = new Transaction();
            transaction.setTransactionType(TransactionType.REFUND);
            transaction.setTransactionAmount(amount);
            transaction.setSenderId(travelerId);
            transaction.setReceiverId(null);
            return transactionRepository.save(transaction);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Transaction withdraw(Withdraw withdraw) throws Exception {
        try {
            String ibanNumber = withdraw.getIbanNumber();

            if (ibanNumber == null) {
                throw new IllegalArgumentException("IBAN number cannot be null");
            }

            if (!IbanChecker.isValidIBAN(ibanNumber)) {
                throw new IllegalArgumentException("Invalid IBAN number");
            }

            int travelerId = withdraw.getTravelerId();

            Optional<Traveler> traveler = accountRepository.findTravelerByUserId(travelerId);

            if (traveler.isEmpty()) {
                throw new IllegalArgumentException("Invalid traveler id");
            }

            BigDecimal amount = withdraw.getAmount();

            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Invalid amount");
            }

            accountRepository.incrementTravelerBalance(travelerId, amount);

            Transaction transaction = new Transaction();
            transaction.setTransactionType(TransactionType.WITHDRAW);
            transaction.setTransactionAmount(amount);
            transaction.setSenderId(null);
            transaction.setReceiverId(travelerId);
            return transactionRepository.save(transaction);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
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

