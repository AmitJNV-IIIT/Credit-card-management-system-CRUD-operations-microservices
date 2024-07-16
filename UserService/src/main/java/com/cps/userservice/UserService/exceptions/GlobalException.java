package com.cps.userservice.UserService.exceptions;

import com.cps.userservice.UserService.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(com.cps.userservice.UserService.exceptions.ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundExceptions(com.cps.userservice.UserService.exceptions.ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.builder().success(false).message(ex.getMessage()).status(null).build());
    }
}
