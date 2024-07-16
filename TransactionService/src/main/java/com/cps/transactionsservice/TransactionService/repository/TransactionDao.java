package com.cps.transactionsservice.TransactionService.repository;

import com.cps.transactionsservice.TransactionService.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionDao extends JpaRepository<Transaction, String>{
    List<Transaction> findByCardId(String cardId);


}
