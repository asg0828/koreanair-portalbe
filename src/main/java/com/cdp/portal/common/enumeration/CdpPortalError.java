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
    GOOGLE_ACCESS_TOKEN_REQUIRED("E0007", "GOOGLE_ACCESS_TOKEN_REQUIRED", HttpStatus.UNAUTHORIZED),
    GOOGLE_ID_TOKEN_REQUIRED("E0008", "GOOGLE_ID_TOKEN_REQUIRED", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED("E0009", "SESSION_EXPIRE", HttpStatus.UNAUTHORIZED),
    SESSION_EXPIRE("E0010", "SESSION_EXPIRE", HttpStatus.FORBIDDEN),
    RETRIEVE_EMPLOYEE_INFO_ERROR("E0011", "RETRIEVE_EMPLOYEE_INFO_ERROR", HttpStatus.UNAUTHORIZED),
    INVALID_GOOGLE_ID_TOKEN("E00012", "INVALID_GOOGLE_ID_TOKEN", HttpStatus.UNAUTHORIZED),

    // code
    CODE_NOT_FOUND("E0101", "Request (codeId: {0}) on (groupId: {1}) is not found", HttpStatus.NOT_FOUND),
    GROUP_NOT_FOUND("E0102", "(groupId: {0}) is duplicated", HttpStatus.BAD_REQUEST),

    // table
    MTS_EN_NM_DUPLICATED("E0201", "테이블정의영문명({0})은 이미 등록되어있습니다.", HttpStatus.BAD_REQUEST),
    TABLE_SPEC_NOT_FOUND("E0202", "테이블정의 ID({0})를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

    // feature
    FEATURE_KO_NM_DUPLICATED("E0301", "Feature 한글명({0})은 이미 등록되어있습니다.", HttpStatus.BAD_REQUEST),
    FEATURE_EN_NM_DUPLICATED("E0302", "Feature 영문명({0})은 이미 등록되어있습니다.", HttpStatus.BAD_REQUEST),
    FEATURE_NOT_FOUND("E0303", "피쳐 ID({0})를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

    // user
    USER_FEATURE_DUPLICATED("E0401", "관심 Feature에 이미 등록되어있습니다.", HttpStatus.BAD_REQUEST),
    USER_FEATURE_NOT_EXISTS("E0402", "관심 Feature에 등록 되어있지않습니다.", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTS("E0403", "존재 하지 않는 사용자 입니다.", HttpStatus.BAD_REQUEST),

	// dept
	DEPT_CODE_DUPLICATED("E0501", "부서 코드({0})은 이미 등록되어있습니다.", HttpStatus.BAD_REQUEST),
	DEPT_CODE_NOT_FOUND("E0502", "부서 코드({0})를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),

	// auth
	AUTH_NOT_FOUND("E0602", "권한 ID({0})를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),

	// menu
	MENU_ID_NOT_FOUND("E0702", "메뉴 ID({0})를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),
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
