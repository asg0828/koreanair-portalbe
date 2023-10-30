package com.cdp.portal.common.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "API 공통 응답")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResDto<T> {
    private static final String STATUS_SUCCESS = "success";
    private static final String STATUS_FAIL = "fail";
    private static final String STATUS_ERROR = "error";
    
    @Schema(description = "상태", example = "success|fail|error", nullable = false)
    private String status;
    
    @Schema(description = "데이터", example = "", nullable = true)
    private T data;
    
    @Schema(description = "메시지", example = "", nullable = true)
    private String message;
    
    public static <T> ApiResDto<T> success(T data) {
        return new ApiResDto<T>(STATUS_SUCCESS, data, null);
    }
    
    public static ApiResDto<?> success() {
        return new ApiResDto<>(STATUS_SUCCESS, null, null);
    }
    
    public static ApiResDto<?> fail(BindingResult bindingResult) {
        List<HashMap<String, Object>> fieldErrors = new ArrayList<>();
        HashMap<String, Object> errors = null;

        List<ObjectError> allErrors = bindingResult.getAllErrors();
        for (ObjectError error : allErrors) {
            errors = new HashMap<>();
            
            if (error instanceof FieldError) {
                errors.put("field", ((FieldError) error).getField());
                errors.put("value", error.getDefaultMessage());
            } else {
                errors.put("field", error.getObjectName());
                errors.put("value", error.getDefaultMessage());
            }
            
            fieldErrors.add(errors);
            
        }
        return new ApiResDto<>(STATUS_FAIL, fieldErrors, null);
    }

    public static ApiResDto<?> error(String message) {
        return new ApiResDto<>(STATUS_ERROR, null, message);
    }

}
