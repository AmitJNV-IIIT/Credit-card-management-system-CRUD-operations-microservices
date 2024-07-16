package com.cps.gatewayservice.entities;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Login {
    private String username;
    private String password;
}
