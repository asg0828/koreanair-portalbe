package com.cdp.portal.app.facade.egroup.model;

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
@Schema(description = "사용자 예외그룹 모델")
public class EgroupModel {

    @Schema(description = "그룹 코드", nullable = false)
    private String groupCode;

    @Schema(description = "그룹 명", example = "", nullable = false)
    private String groupNm;

    @Schema(description = "상위 그룹코드", example = "")
    private String upGroupCode;

    @Schema(description = "순서", example = "")
    private int ordSeq;

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
