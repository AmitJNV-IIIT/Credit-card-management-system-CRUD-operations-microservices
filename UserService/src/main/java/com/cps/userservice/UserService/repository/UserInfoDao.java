package com.cps.userservice.UserService.repository;

import com.cps.userservice.UserService.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoDao extends JpaRepository<UserInfo, String> {
}
