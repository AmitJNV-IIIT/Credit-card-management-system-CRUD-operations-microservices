package com.cps.cardservice.CardService.external.services;

import com.cps.cardservice.CardService.entities.Transaction;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface TransactionService {

    @PostMapping("/api/v1/transactions")
    public Transaction createTransaction(@RequestBody Transaction transaction);
}
