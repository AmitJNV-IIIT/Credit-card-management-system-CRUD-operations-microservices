package com.cps.userservice.UserService.service;

import com.cps.userservice.UserService.entities.Card;
import com.cps.userservice.UserService.entities.UserInfo;
import com.cps.userservice.UserService.entities.Users;
import com.cps.userservice.UserService.exceptions.ResourceNotFoundException;
import com.cps.userservice.UserService.repository.UserDao;
import com.cps.userservice.UserService.repository.UserInfoDao;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RestTemplate restTemplate;

    public UserInfo saveUserInfo(UserInfo userInfo) {
        String randomUserId = UUID.randomUUID().toString();
        userInfo.setUserId(randomUserId);
        // Updating Users table
        Users user = new Users();
        user.setUser_id(randomUserId);
        user.setUsername(userInfo.getUsername());
        user.setPassword(userInfo.getPassword());
        user.setRole_id(3); // 1=SuperAdmin, 2=Admin, 3=User
        user.setAccount_status("active");
        userDao.save(user);
        // Save to UserInfo table and return result
        return this.userInfoDao.save(userInfo);
    }

    public List<UserInfo> getAllUserInfo() {
        if(!userInfoDao.findAll().isEmpty()){
            return userInfoDao.findAll();
        } else{
            throw new ResourceNotFoundException("No users found on server!");
        }
    }

    public UserInfo getUserInfoById(String userId) {
        UserInfo userInfo = userInfoDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        ArrayList<Card> cards = restTemplate.getForObject("http://host.docker.internal:8082/api/v1/cards/users/" + userId, ArrayList.class);
        userInfo.setCards(cards);
        return userInfo;
    }

    public UserInfo updateUserInfoById(UserInfo user) {
        return userInfoDao.save(user);
    }

    public void deleteUserInfoById(String userId) {
        userInfoDao.deleteById(userId);
    }


}
