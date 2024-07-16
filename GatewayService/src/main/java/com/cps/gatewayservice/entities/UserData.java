package com.cps.gatewayservice.entities;

import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserData {
    private String role_id;
    private String added_by;
    private String resources;
    private String role;
    private String userId;
    private String phone;
    private String password;
    private String username;
    private String name;
    private String email;
    private Date dob ;
    private Timestamp creationDate;
    private String jwt;
    private String account_status;
}
