package com.cps.gatewayservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Users {
    @Id
    private int id;
    private String username;
    private String password;
    private String jwt;
    private String account_status;
    private int role_id;
    private String user_id;
}
