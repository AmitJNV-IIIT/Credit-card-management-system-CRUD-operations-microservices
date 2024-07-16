package com.cps.userservice.UserService.repository;

import com.cps.userservice.UserService.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<Users, String> {
}
