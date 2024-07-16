package com.cps.transactionsservice.TransactionService.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.*;

import java.sql.Timestamp;

@Entity(name="transactions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Transaction {
    @Id
    private String transactionId;
    private double amount;
    private boolean isFraudulent;
    private String merchantId;
    private String notes;
    private String productCategory;
    private Timestamp transactionDate;
    private String transactionType = "online"; // E.g. online, pos, refund, etc.
    private String cardId;
    private String userId;

    @PrePersist
    private void setTransactionDate() {
        // set transaction timestamp
        this.transactionDate = new Timestamp(System.currentTimeMillis());
    }
}
