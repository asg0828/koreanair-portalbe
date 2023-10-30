package com.cdp.portal.common.enumeration;

import java.text.MessageFormat;
import java.util.Arrays;

import org.springframework.http.HttpStatus;

import com.cdp.portal.common.error.exception.CdpPortalException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CdpPortalError {
    // common
    INVALID_INPUT_VALUE("E0001", "Invalid Input Value", HttpStatus.BAD_REQUEST),
    INVALID_TYPE_VALUE("E0002", "Invalid Type Value", HttpStatus.BAD_REQUEST),
    RESOURCE_NOT_FOUND("E0003", "Resource not found", HttpStatus.NOT_FOUND),
    METHOD_NOT_ALLOWED("E0004", "Method Not Allowed", HttpStatus.METHOD_NOT_ALLOWED),
    INTERNAL_SERVER_ERROR("E0005", "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
    
    // code
    CODE_NOT_FOUND("E0101", "Request (codeId: {0}) on (groupId: {1}) is not found", HttpStatus.NOT_FOUND),
    GROUP_NOT_FOUND("E0102", "(groupId: {0}) is duplicated", HttpStatus.BAD_REQUEST),
    
    // feature
    FEATURE_NOT_FOUND("E0201", "(featureId: {0}) is not found", HttpStatus.NOT_FOUND),
    ;
    
    private String code;
    private String message;
    private HttpStatus status;
    
    public CdpPortalException exception() {
        return new CdpPortalException(this);
    }
    
    public CdpPortalException exception(String... args) {
        return new CdpPortalException(this, args);
    }
    
    public String format(String... args) {
        if (args.length > 0) {
            if (message.split("\\{").length - 1 != args.length) {
                return message.concat(" ").concat(Arrays.toString(args));
            } else {
                return MessageFormat.format(message, args);
            }
        }
        
        return message;
    }
    
}
