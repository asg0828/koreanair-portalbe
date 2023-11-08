package com.cdp.portal.app.facade.feature.model;

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
@Schema(description = "피쳐 모델")
public class FeatureModel {
    
    @Schema(description = "피쳐ID", example = "", nullable = false)
    private String featureId;
    
    @Schema(description = "피쳐타입", example = "", nullable = false)
    private String featureTyp;
    
    @Schema(description = "피쳐구분", example = "", nullable = false)
    private String featureSe;
    
    @Schema(description = "피쳐한글명", example = "", nullable = false)
    private String featureKoNm;
    
    @Schema(description = "피쳐영문명", example = "", nullable = false)
    private String featureEnNm;
    
    @Schema(description = "산출단위", example = "")
    private String calcUnt;
    
    @Schema(description = "피쳐정의", example = "", nullable = false)
    private String featureDef;
    
    @Schema(description = "피쳐산식(산출로직)", example = "")
    private String featureFm;
    
    @Schema(description = "신청자 ID", example = "", nullable = false)
    private String enrUserId;
    
    @Schema(description = "신청부서코드", example = "")
    private String enrDeptCode;
    
    @Schema(description = "연관테이블", example = "")
    private String featureRelTb;
    
    @Schema(description = "비고", example = "")
    private String featureDsc;
    
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
