package com.bdp.ap.app.audit.model;

import lombok.Data;

@Data
public class UsingTableModel {

    private int rownum;             // 화면 순번
    private String tableId;         // 테이블아이디
    private String datasetId;       // 데이터셋아이디

    private String companyCd;        // 제공회사코드
    private String companyNm;        // 제공회사명

    private String jobType;          // 업무구분
    private String dbType;           // DB유형
    private String tableNameKr;     // 테이블명(한국어)
    private String tableNameEn;     // 테이블명(영어)
    private String tableDesc;       // 테이블 설명

}
