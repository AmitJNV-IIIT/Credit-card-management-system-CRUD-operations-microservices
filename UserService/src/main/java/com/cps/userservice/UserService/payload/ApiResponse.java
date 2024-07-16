package com.cps.userservice.UserService.payload;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder // Used when we want to use builder pattern.
public class ApiResponse {
    private boolean success;
    private String message;
    private HttpStatus status;

}
