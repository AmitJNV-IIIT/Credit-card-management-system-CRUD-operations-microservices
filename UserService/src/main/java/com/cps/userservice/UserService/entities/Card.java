package com.cps.userservice.UserService.entities;

import jakarta.persistence.PrePersist;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Card {
    private String cardId;
    private String cardNumber;
    private String cardNickname;
    private String creationDate;
    private String cvv;
    private Date expiryDate;
    private boolean isDefault = false;
    private String isEnabled;
    private Date issueDate;
    private String zip;
    private String userId;

    @PrePersist
    private void setCreationDate() {
        // set today's date
        this.creationDate = LocalDate.now().toString();
    }
}
