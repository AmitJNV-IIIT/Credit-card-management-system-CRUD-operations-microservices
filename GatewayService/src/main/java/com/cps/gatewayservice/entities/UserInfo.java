package com.cps.gatewayservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

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
}
