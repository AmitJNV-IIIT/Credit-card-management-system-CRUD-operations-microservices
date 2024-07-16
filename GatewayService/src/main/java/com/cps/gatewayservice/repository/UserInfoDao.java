package com.cps.gatewayservice.repository;

import com.cps.gatewayservice.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoDao extends JpaRepository<UserInfo, String> {
}
