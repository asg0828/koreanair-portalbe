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
@Schema(description = "피쳐 구분 모델")
public class FeatureSeparateModel {
    
    @Schema(description = "구분그룹ID", example = "SE_GRP_ID")
    private String seGrpId;
    
    @Schema(description = "구분ID", example = "CUSTOMER_SERVICE")
    private String seId;
    
    @Schema(description = "구분명", example = "고객서비스")
    private String seNm;
    
    @Schema(description = "구분설명", example = "")
    private String seDsc;
    
    @Schema(description = "정렬순서", example = "1")
    private Integer ordSeq;
    
    @Schema(description = "사용여부", example = "Y")
    private String useYn;
    
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
