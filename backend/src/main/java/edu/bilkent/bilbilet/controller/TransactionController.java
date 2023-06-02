package edu.bilkent.bilbilet.controller;

import edu.bilkent.bilbilet.exception.ExceptionLogger;
import edu.bilkent.bilbilet.model.Address;
import edu.bilkent.bilbilet.model.Transaction;
import edu.bilkent.bilbilet.request.transaction.*;
import edu.bilkent.bilbilet.response.Response;
import edu.bilkent.bilbilet.service.TransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "")
    public ResponseEntity<Object> createTransaction(@Valid @RequestBody Transaction transaction) {
        try {
            Transaction createdTransaction = transactionService.createTransaction(transaction);
            return Response.create("Transaction created successfully", HttpStatus.OK, createdTransaction);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/add-funds")
    public ResponseEntity<Object> addFunds(@Valid @RequestBody AddFunds addFunds) {
        try {
            Transaction createdTransaction = transactionService.addFunds(addFunds);
            return Response.create("Added funds successfully", HttpStatus.OK, createdTransaction);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "balance-payment")
    public ResponseEntity<Object> balancePayment(@Valid @RequestBody BalancePayment balancePayment) {
        try {
            Transaction createdTransaction = transactionService.balancePayment(balancePayment);
            return Response.create("Payment successful", HttpStatus.OK, createdTransaction);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "card-payment")
    public ResponseEntity<Object> cardPayment(@Valid @RequestBody CardPayment cardPayment) {
        try {
            Transaction createdTransaction = transactionService.cardPayment(cardPayment);
            return Response.create("Payment successful", HttpStatus.OK, createdTransaction);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path ="refund")
    public ResponseEntity<Object> refund(@Valid @RequestBody Refund refund) {
        try {
            Transaction createdTransaction = transactionService.refund(refund);
            return Response.create("Refunded successfully", HttpStatus.OK, createdTransaction);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path ="withdraw")
    public ResponseEntity<Object> withdraw(@Valid @RequestBody Withdraw withdraw) {
        try {
            Transaction createdTransaction = transactionService.withdraw(withdraw);
            return Response.create("Withdrew funds successfully", HttpStatus.OK, createdTransaction);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("sender/{senderId}")
    public ResponseEntity<Object> getTransactionsBySenderId(@PathVariable int senderId) {
        try {
            List<Transaction> transactions = transactionService.getTransactionsBySenderId(senderId);
            return Response.create("Transactions found", HttpStatus.OK, transactions);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("receiver/{receiverId}")
    public ResponseEntity<Object> getTransactionsByReceiverId(@PathVariable int receiverId) {
        try {
            List<Transaction> transactions = transactionService.getTransactionsByReceiverId(receiverId);
            return Response.create("Transactions found", HttpStatus.OK, transactions);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("user/{userId}")
    public ResponseEntity<Object> getAllTransactionsByUserId(@PathVariable int userId) {
        try {
            List<Transaction> transactions = transactionService.getAllTransactionsByUserId(userId);
            return Response.create("Transactions found", HttpStatus.OK, transactions);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("{transactionId}")
    public ResponseEntity<Object> getTransactionById(@PathVariable int transactionId) {
        try {
            Transaction transaction = transactionService.getTransactionById(transactionId);
            return Response.create("Transaction found", HttpStatus.OK, transaction);
        } catch (Exception e) {
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


