package com.cdp.portal.app.facade.user.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Schema(description = "사용자 모델")
public class UserModel {

    @Schema(description = "사용자 ID", nullable = false)
    private String userId;

    @Schema(description = "사용자 명", nullable = false)
    private String userNm;

    @Schema(description = "사용자 이메일", example = "")
    private String userEmail;

    @Schema(description = "부서(팀)코드", example = "")
    private String deptCode;

    @Schema(description = "부서(팀)명", example = "")
    private String deptNm;

    @Schema(description = "상위 부서코드", example = "")
    private String upDeptCode;

    @Schema(description = "상위 부서명", example = "")
    private String upDeptNm;

    @Schema(description = "그룹코드(예외)", example = "")
    private String groupCode;

    @Schema(description = "그룹명(예외)", example = "")
    private String groupNm;

    @Schema(description = "회사코드", example = "")
    private String companyCode;

    @Schema(description = "마지막 로그인 일시", example = "2021-04-13 09:04:40")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String lastLogDt;

    @Schema(description = "이전 부서(팀)코드", example = "")
    private String bfDeptCode;

    @Schema(description = "부서(팀) 변경 일시", example = "2021-04-13 09:04:40")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String deptUpdtDt;

    @Schema(description = "이전 상위 부서코드", example = "")
    private String bfUpDeptCode;

    @Schema(description = "상위 부서 변경 일시", example = "2021-04-13 09:04:40")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String upDeptUpdtDt;

    @Schema(description = "이전 그룹코드(예외)", example = "")
    private String bfGroupCode;

    @Schema(description = "그룹코드(예외) 변경 일시", example = "2021-04-13 09:04:40")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String groupUpdtDt;

    @Schema(description = "재직구분", example = "Y")
    private String useYn;

    @Schema(description = "수정구분", example = "")
    private String modiSe;

    @Schema(description = "등록자ID", example = "admin")
    private String rgstId;

    @Schema(description = "등록일시", example = "2021-04-13 09:04:40")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rgstDt;

    @Schema(description = "수정자ID", example = "admin")
    private String modiId;

    @Schema(description = "수정일시", example = "2021-04-13 09:04:40")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modiDt;
}
