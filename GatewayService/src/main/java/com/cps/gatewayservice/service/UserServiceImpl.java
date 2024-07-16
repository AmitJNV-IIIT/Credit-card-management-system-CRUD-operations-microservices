package com.cps.gatewayservice.service;

import com.cps.gatewayservice.entities.*;
import com.cps.gatewayservice.exceptions.ResourceNotFoundException;
import com.cps.gatewayservice.repository.RoleDao;
import com.cps.gatewayservice.repository.UserDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.sql.Timestamp;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RestTemplate restTemplate;
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);;
    public UserData getUserDataByUsername(String username, String password) {
        Users user = userDao.findByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with username: " + username);
        }
        String userId = user.getUser_id();
        String userInfoServiceBaseUrl = "http://host.docker.internal:8081/api/v1/users/";
        UserInfo userInfo = restTemplate.getForObject(userInfoServiceBaseUrl + userId, UserInfo.class);
        Roles role = roleDao.findById(String.valueOf(user.getRole_id())).orElseThrow();
        if(userInfo == null) {
            throw new ResourceNotFoundException("User info not found with user id: " + userId);
        }
        if(role == null) {
            throw new ResourceNotFoundException("Role not found with role id: " + user.getRole_id());
        }
        if (!(userInfo.getPassword().equals(password))) {
            throw new ResourceNotFoundException("Invalid password for username: " + username);
        }

        System.out.println("User Info: " + userInfo);
        System.out.println("Role: " + role);

        return getUserData(role, userInfo, user);
    }

    private UserData getUserData(Roles role, UserInfo userInfo, Users user) {
        UserData userData = new UserData();

        userData.setRole_id(role.getRole_id());
        userData.setAdded_by(role.getAdded_by());
        userData.setResources(role.getResources());
        userData.setRole(role.getRole());
        userData.setUserId(userInfo.getUserId());
        userData.setPhone(userInfo.getPhone());
        userData.setPassword("********");
        userData.setUsername(user.getUsername());
        userData.setName(userInfo.getName());
        userData.setEmail(userInfo.getEmail());
        userData.setDob(userInfo.getDob());
        userData.setCreationDate(userInfo.getCreationDate());
        userData.setJwt(user.getJwt());
        userData.setAccount_status(user.getAccount_status());
        return userData;
    }

    public String generateJwtToken(Login login) {

        String username = login.getUsername();
        String password = login.getPassword();
        UserData userData = getUserDataByUsername(username, password);

        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + 1800000; // 30 minutes in milliseconds

        String userSubject = convertUserDataToJson(userData);

        return Jwts.builder()
                .setSubject(userSubject)
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(new Date(expMillis))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String getSubjectFromToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return claims.getSubject();
    }

    private String convertUserDataToJson(UserData userData) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(userData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting UserData to JSON", e);
        }
    }

}
