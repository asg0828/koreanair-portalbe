package com.bdp.ap.app.code.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * 코드관리 모델
 */
@Data
public class CodeModel {

    private String rownum;          // 순번
    private String groupId;         // 코드그룹ID
    private String codeId;          // 코드ID
    private String codeNm;          // 코드이름
    private String codeDsc;        // 코드설명
    private String useYn;           // 사용여부
    private String rgstId;          // 등록자ID
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  // spring eval taglib를 사용하여 annotation 패턴으로 출력 가능
    private LocalDateTime rgstDt; // 등록일시
    private String rgstDtStr;
    private String modiId;          // 수정자ID
    private LocalDateTime modiDt; // 수정일시
    private Integer ordSeq;			//정렬순서
    private Integer lv;				// 레벨
}
