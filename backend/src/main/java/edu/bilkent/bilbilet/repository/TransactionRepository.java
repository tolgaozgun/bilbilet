package edu.bilkent.bilbilet.repository;

import edu.bilkent.bilbilet.enums.TransactionType;
import edu.bilkent.bilbilet.model.Hotel;
import edu.bilkent.bilbilet.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Qualifier("transaction_repo")
@Repository
public class TransactionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Transaction> transactionRowMapper = (rs, rowNum) -> {
        Transaction transaction = new Transaction();
        transaction.setTransaction_id(rs.getInt("transaction_id"));
        transaction.setTransaction_type(TransactionType.valueOf(rs.getString("transaction_type")));
        transaction.setTransaction_amount(rs.getBigDecimal("transaction_amount"));
        transaction.setReceiver_id(rs.getInt("receiver_id"));
        transaction.setSender_id(rs.getInt("sender_id"));
        return transaction;
    };


    public List<Transaction> findTransactionsBySenderId(int senderId) {
        String sql = "SELECT * FROM Transactions WHERE sender_id = ?";

        try {
            return jdbcTemplate.query(sql, transactionRowMapper, senderId);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<Transaction> findTransactionsByReceiverId(int receiverId) {
        String sql = "SELECT * FROM Transactions WHERE receiver_id = ?";

        try {
            return jdbcTemplate.query(sql, transactionRowMapper, receiverId);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<Transaction> findAllTransactionsOfId(int userId){
        String sql = "SELECT * FROM Transactions WHERE receiver_id = ? OR sender_id = ?";

        try {
            return jdbcTemplate.query(sql, transactionRowMapper, userId, userId);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();

    }

    public Optional<Transaction> findTransactionById(int transactionId) {
        String sql = "SELECT * FROM Transactions WHERE transaction_id = ?";

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, transactionRowMapper, transactionId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    public Transaction save(Transaction transaction) {
        String sql = "INSERT INTO Transactions (transaction_id, transaction_type, transaction_amount, receiver_id, sender_id) "
                + "VALUES (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] { "transaction_id" });
            ps.setInt(1, transaction.getTransaction_id());
            ps.setString(2, transaction.getTransaction_type().toString());
            ps.setBigDecimal(3, transaction.getTransaction_amount());
            // Check if receiver_id or sender_id is null
            if (transaction.getReceiver_id() == null) {
                ps.setNull(4, java.sql.Types.INTEGER);
            } else {
                ps.setInt(4, transaction.getReceiver_id());
            }

            if (transaction.getSender_id() == null) {
                ps.setNull(5, java.sql.Types.INTEGER);
            } else {
                ps.setInt(5, transaction.getSender_id());
            }

            return ps;
        }, keyHolder);

        int generatedId = keyHolder.getKey().intValue();
        Transaction newTransaction = findTransactionById(generatedId).orElse(null);
        return newTransaction;
    }

}