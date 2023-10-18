package com.bdp.ap.app.extrnl.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * 외부시스템 모델
 */
@Data
public class ExtrnlModel {

    private String rownum;              // 순번
    private String extrnlId;            // 외부시스템ID
    private String extrnlNm;            // 외부시스템명
    private String extrnlUrl;           // 외부시스템URL
    private String extrnlDsc;          // 외부시스템설명
    private String useYn;               // 사용여부
    private String rgstId;              // 등록자ID
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rgstDt;     // 등록일시
    private String modiId;              // 수정자ID
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modiDt;     // 수정일시
}
