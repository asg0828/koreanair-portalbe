package com.cdp.portal.common.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * API 공통 응답
 * success: request 기반 작업에 대해 모두 잘 진행되었으며, response data가 returned.
 * fail: request 때 제출된 데이터에 문제가 있거나, API 호출의 일부 조건에 대해 맞지 않음.
 * error: request을 처리하는 중에 오류가 발생했습니다 (예 : exception 발생)
 * 
 * 참조: https://github.com/omniti-labs/jsend
 * @param <T>
 */
@Schema(description = "API 공통 응답")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResDto<T> {
    private static final String STATUS_SUCCESS = "success";
    private static final String STATUS_FAIL = "fail";
    private static final String STATUS_ERROR = "error";
    
    @Schema(description = "상태\n\n"
            + "success: request 기반 작업에 대해 모두 잘 진행되었으며, response data가 returned\n\n"
            + "fail: request때 제출된 데이터에 문제가 있거나, API 호출의 일부 조건에 대해 맞지 않음\n\n"
            + "error: request를 처리하는 중에 오류가 발생했습니다(예: exception 발생)", example = "success|fail|error", nullable = false)
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

    public static ApiResDto<?> fail(String message) {
        return new ApiResDto<>(STATUS_FAIL, null, message);
    }

    public static ApiResDto<?> error(String message) {
        return new ApiResDto<>(STATUS_ERROR, null, message);
    }

}
