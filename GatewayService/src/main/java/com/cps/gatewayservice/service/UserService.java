package com.cps.gatewayservice.service;

import com.cps.gatewayservice.entities.*;
import org.springframework.stereotype.Service;

public interface UserService {
    public UserData getUserDataByUsername(String username, String password);
    public String generateJwtToken(Login login);
    public String getSubjectFromToken(String token);
}
