package com.cps.userservice.UserService.controller;

import com.cps.userservice.UserService.entities.UserInfo;
import com.cps.userservice.UserService.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/users")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping
    public ResponseEntity<UserInfo> createUser(@RequestBody UserInfo userInfo) {
        return ResponseEntity.ok(userInfoService.saveUserInfo(userInfo));
    }

    @GetMapping()
    public ResponseEntity<List<UserInfo>> getAllUsers() {
        return ResponseEntity.ok(userInfoService.getAllUserInfo());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserInfo> getSingleUser(@PathVariable String userId) {
//        return ResponseEntity.ok(userInfoService.getUserInfoById(userId));
        UserInfo userInfo = userInfoService.getUserInfoById(userId);
        return ResponseEntity.ok(userInfo);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserInfo> updateUserInfoById(@RequestBody UserInfo userInfo, @PathVariable String userId) {
        userInfo.setUserId(userId);
        return ResponseEntity.ok(userInfoService.updateUserInfoById(userInfo));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserInfoById(@PathVariable String userId) {
        userInfoService.deleteUserInfoById(userId);
        return ResponseEntity.ok().build();
    }



}
