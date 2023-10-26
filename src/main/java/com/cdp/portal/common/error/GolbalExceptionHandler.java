package com.cdp.portal.common.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cdp.portal.common.dto.ApiResDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
@RestController
public class GolbalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResDto<?>> handleValidationExceptions(MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException : {}", e);
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResDto.fail(e.getBindingResult()));
    }
    
    

}
