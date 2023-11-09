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
@Schema(description = "테이블 컬럼 정의서 모델")
public class TableColumnSpecModel {
    
    @Schema(description = "테이블정의ID", example = "mt23000000001", nullable = false)
    private String mtsId;
    
    @Schema(description = "컬럼정의ID", example = "mc23000000003", nullable = false)
    private String mcsId;
    
    @Schema(description = "컬럼정의한글명", example = "노선ID", nullable = false)
    private String mcsKoNm;
    
    @Schema(description = "컬럼정의영문명", example = "route_id", nullable = false)
    private String mcsEnNm;
    
    @Schema(description = "정의", example = "", nullable = false)
    private String mcsDef;
    
    @Schema(description = "원천컬럼명", example = "")
    private String srcClNm;
    
    @Schema(description = "컬럼산출로직", example = "")
    private String clFm;
    
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
