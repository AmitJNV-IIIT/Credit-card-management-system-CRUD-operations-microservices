package com.cps.gatewayservice.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.cps.gatewayservice.entities.Login;
import com.cps.gatewayservice.service.UserService;
import com.cps.gatewayservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class GatewayController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> generateToken(@RequestBody Login login) {
        String token = userService.generateJwtToken(login);
        String completeToken = "Bearer " + token;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiry = now.plusMinutes(30); // Expiry after 30 minutes
        long createdTimestamp = now.toEpochSecond(ZoneOffset.UTC);
        long expiryTimestamp = expiry.toEpochSecond(ZoneOffset.UTC);

        // Create a map to represent the JSON response
        Map<String, Object> response = new HashMap<>();
        response.put("Token", completeToken);
        response.put("Username", login.getUsername());
        response.put("Created", createdTimestamp);
        response.put("Expiry", expiryTimestamp);

        // Convert the map to a JSON string
        String jsonResponse;
        try {
            jsonResponse = new ObjectMapper().writeValueAsString(response);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred while generating JSON response", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(jsonResponse);
    }

    @PostMapping(value = "/services/decrypt", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> decrypt(@RequestBody Map<String, Object> payload) {
        String token = (String) payload.get("Token");
        if (token == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token not provided");
        }

        System.out.println("Encrypted Token: " + token);
        token = token.replace("Bearer ", "");

        String decryptedString = userService.getSubjectFromToken(token);
        return ResponseEntity.ok(decryptedString);
    }

}
