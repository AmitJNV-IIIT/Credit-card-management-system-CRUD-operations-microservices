package com.cps.gatewayservice.exceptions;


import com.cps.gatewayservice.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(com.cps.gatewayservice.exceptions.ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundExceptions(com.cps.gatewayservice.exceptions.ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.builder().success(false).message(ex.getMessage()).status(null).build());
    }
}
