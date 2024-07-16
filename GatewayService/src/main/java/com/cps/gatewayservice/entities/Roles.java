package com.cps.gatewayservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name="roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Roles {
    @Id
    private String role_id;
    private String added_by;
    private String resources;
    private String role;
}
