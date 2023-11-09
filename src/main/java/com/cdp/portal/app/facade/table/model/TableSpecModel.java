package com.cdp.portal.app.facade.table.model;

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
@Schema(description = "테이블 정의서 모델")
public class TableSpecModel {
    
    @Schema(description = "테이블정의ID", example = "mt23000000001", nullable = false)
    private String mtsId;
    
    @Schema(description = "테이블정의한글명", example = "", nullable = false)
    private String mtsKoNm;
    
    @Schema(description = "테이블정의영문명", example = "", nullable = false)
    private String mtsEnNm;
    
    @Schema(description = "정의", example = "", nullable = false)
    private String mtsDef;
    
    @Schema(description = "원천시스템", example = "")
    private String srcSys;
    
    @Schema(description = "원천테이블명", example = "")
    private String srcTbNm;
    
    @Schema(description = "DB코드", example = "")
    private String srcDbCd;
    
    @Schema(description = "비고", example = "")
    private String mtsDsc;
    
    @Schema(description = "삭제여부", example = "")
    private String delYn;
    
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
