package com.bdp.ap.app.audit.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class AuditDataSetModel {
    private int rownum;             // 화면 순번
    private String datasetId;       // 데이터셋 아이디
    private String datasetType;          //데이터셋 타입
    private String userId;          // 아이디
    private String userNm;          // 성명
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDt;	    // 시작일자
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDt;	    // 종료일자
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate extDt;	    // 마지막 연장일자
    private String status;          // 상태

}
