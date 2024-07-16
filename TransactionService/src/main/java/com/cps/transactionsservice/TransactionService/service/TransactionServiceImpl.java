package com.cps.transactionsservice.TransactionService.service;

import com.cps.transactionsservice.TransactionService.entities.Transaction;
import com.cps.transactionsservice.TransactionService.exceptions.ResourceNotFoundException;
import com.cps.transactionsservice.TransactionService.repository.TransactionDao;
import com.cps.transactionsservice.TransactionService.entities.Transaction;
import com.cps.transactionsservice.TransactionService.repository.TransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionDao transactionDao;

    public Transaction saveTransaction(Transaction transaction) {
        String randomTransactionId = UUID.randomUUID().toString();
        transaction.setTransactionId(randomTransactionId);
        return this.transactionDao.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        if(!transactionDao.findAll().isEmpty()){
            return transactionDao.findAll();
        } else{
            throw new ResourceNotFoundException("No transaction found on card!");
        }
    }

    public List<Transaction> getTransactionsByCardId(String cardId) {
        return transactionDao.findByCardId(cardId);
    }

    public Transaction getTransactionById(String transactionId) {
        return transactionDao.findById(transactionId).orElseThrow(() -> new ResourceNotFoundException("Card not found with id: " + transactionId));
    }

    public Transaction updateTransactionById(Transaction card) {
        return transactionDao.save(card);
    }

    public void deleteTransactionById(String cardId) {
        transactionDao.deleteById(cardId);
    }


}
