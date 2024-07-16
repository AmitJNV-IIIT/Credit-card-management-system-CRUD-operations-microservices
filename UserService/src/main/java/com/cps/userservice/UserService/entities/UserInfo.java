package com.cps.userservice.UserService.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity(name="user_info")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserInfo {
    @Id
    private String userId;
    private String phone;
    private String password;
    private String username;
    private String name;
    private String email;
    private Date dob ;
    private Timestamp creationDate;
    @Transient
    private List<Card> cards = new ArrayList<>();

    @PrePersist
    private void setDefaultCreationDate() {
        this.creationDate = new Timestamp(System.currentTimeMillis());
    }
}
