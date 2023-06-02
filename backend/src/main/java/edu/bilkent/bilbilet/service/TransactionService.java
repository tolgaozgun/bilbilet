package edu.bilkent.bilbilet.service;

import edu.bilkent.bilbilet.model.Transaction;
import edu.bilkent.bilbilet.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

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

