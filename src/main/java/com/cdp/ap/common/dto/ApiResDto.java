package com.cdp.ap.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * API 공통 응답 DTO
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
    
    @Schema(description = "상태", example = "success|fail|error", required = true)
    private String status;
    
    @Schema(description = "데이터", example = "", required = false)
    private T data;
    
    @Schema(description = "메시지", example = "", required = false)
    private String message;
    
    public static <T> ApiResDto<T> createSuccess(T data) {
        return new ApiResDto<T>(STATUS_SUCCESS, data, null);
    }
    
    public static ApiResDto<?> createSuccessWithNoContent() {
        return new ApiResDto<>(STATUS_SUCCESS, null, null);
    }
     

}
