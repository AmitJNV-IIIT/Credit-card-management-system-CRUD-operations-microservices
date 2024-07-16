package com.cps.userservice.UserService.service;

import com.cps.userservice.UserService.entities.UserInfo;

import java.util.List;

public interface UserInfoService {
    public UserInfo getUserInfoById(String id);
    public List<UserInfo> getAllUserInfo();
    public UserInfo saveUserInfo(UserInfo user);
    public UserInfo updateUserInfoById(UserInfo user);
    public void deleteUserInfoById(String id);
}
