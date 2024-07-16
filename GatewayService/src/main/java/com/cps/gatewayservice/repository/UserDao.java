package com.cps.gatewayservice.repository;

import com.cps.gatewayservice.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<Users, String> {
    Users findByUsername(String username);
}
