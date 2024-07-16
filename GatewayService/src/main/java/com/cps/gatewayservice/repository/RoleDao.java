package com.cps.gatewayservice.repository;

import com.cps.gatewayservice.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Roles, String> {
}
