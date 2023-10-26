package com.cdp.portal.app.bo.code.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 코드 모델(모델은 테이블 엔티티와 1:1 맵핑하는걸로)
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CodeModel {
    
    @Schema(description = "그룹ID", example = "GROUP_ID")
    private String groupId;
    
    @Schema(description = "코드ID", example = "APPROVE_YN")
    private String codeId;
    
    @Schema(description = "코드이름ID", example = "승인 여부")
    private String codeNm;
    
    @Schema(description = "코드설명", example = "")
    private String codeDsc;
    
    @Schema(description = "정렬순서", example = "")
    private Integer ordSeq;
    
    @Schema(description = "사용여부", example = "Y")
    private String useYn;
    
    @Schema(description = "등록자ID", example = "SYSTEM")
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
