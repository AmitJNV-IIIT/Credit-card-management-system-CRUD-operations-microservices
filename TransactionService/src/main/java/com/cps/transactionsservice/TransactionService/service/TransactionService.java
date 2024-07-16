package com.cps.transactionsservice.TransactionService.service;

import com.cps.transactionsservice.TransactionService.entities.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    public Transaction getTransactionById(String id);
    public List<Transaction> getAllTransactions();
    public Transaction saveTransaction(Transaction user);
    public Transaction updateTransactionById(Transaction card);
    public void deleteTransactionById(String id);
    public List<Transaction> getTransactionsByCardId(String cardId);
}
