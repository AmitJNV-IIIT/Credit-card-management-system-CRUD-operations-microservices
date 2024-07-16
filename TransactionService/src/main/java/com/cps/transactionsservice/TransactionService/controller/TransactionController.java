package com.cps.transactionsservice.TransactionService.controller;

import com.cps.transactionsservice.TransactionService.entities.Transaction;
import com.cps.transactionsservice.TransactionService.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        return ResponseEntity.ok(transactionService.saveTransaction(transaction));
    }

    @GetMapping()
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getSingleTransaction(@PathVariable String transactionId) {
        return ResponseEntity.ok(transactionService.getTransactionById(transactionId));
    }

    @GetMapping("/cards/{cardId}")
    public ResponseEntity<List<Transaction>> getTransactionsByCardId(@PathVariable String cardId) {
        return ResponseEntity.ok(transactionService.getTransactionsByCardId(cardId));
    }

    @PatchMapping("/{transactionId}")
    public ResponseEntity<Transaction> updateTransactionById(@RequestBody Transaction transaction, @PathVariable String transactionId) {
        transaction.setTransactionId(transactionId);
        return ResponseEntity.ok(transactionService.updateTransactionById(transaction));
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Void> deleteTransactionById(@PathVariable String transactionId) {
        transactionService.deleteTransactionById(transactionId);
        return ResponseEntity.ok().build();
    }


}
