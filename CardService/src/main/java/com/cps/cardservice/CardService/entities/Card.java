package com.cps.cardservice.CardService.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name="cards")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Card {
    @Id
    private String cardId;
    private String cardNumber;
    private String cardNickname;
    private String creationDate;
    private String cvv;
    private String expiryDate;
    private boolean isDefault = false;
    private String isEnabled = "Enabled";
    private String issueDate;
    private String zip;
    private String userId;
    @Transient
    private List<Transaction> transactions = new ArrayList<>();

    @PrePersist
    private void setCreationDate() {
        // set today's date
        this.creationDate = LocalDate.now().toString();
    }

}
