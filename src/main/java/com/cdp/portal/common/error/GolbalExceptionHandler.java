package com.cdp.portal.common.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cdp.portal.common.dto.ApiResDto;
import com.cdp.portal.common.enumeration.CdpPortalError;
import com.cdp.portal.common.error.exception.CdpPortalException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
@RestController
public class GolbalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResDto<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException : {}", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResDto.fail(e.getBindingResult(), CdpPortalError.INVALID_INPUT_VALUE.getMessage()));
    }
    
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResDto<?>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException : {}", e);
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(ApiResDto.error(e.getMessage()));
    }
    
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResDto<?>> handleException(Exception e) {
        log.error("handlerException : {}", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResDto.error(e.getMessage()));
    }
    
    @ExceptionHandler(value = CdpPortalException.class)
    public ResponseEntity<ApiResDto<?>> handleCdpPortalException(CdpPortalException e) {
        log.error("handlerCdpPortalException : {}", e);
        return ResponseEntity.status(e.getError().getStatus()).body(ApiResDto.fail(e.getMessage()));
    }

}
