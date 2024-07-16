package com.cps.cardservice.CardService.entities;

import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private String transactionId;
    private double amount;
    private boolean isFraudulent;
    private String merchantId;
    private String notes;
    private String productCategory;
    private Timestamp transactionDate;
    private String transactionType;
    private String cardId;
    private String userId;

    @PrePersist
    private void setTransactionDate() {
        // set transaction timestamp
        this.transactionDate = new Timestamp(System.currentTimeMillis());
    }
}
