package com.bdp.ap.app.bizmeta.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Bizmeta 표준계수모델
 */
@Data
public class BizmetaModel {

    private String stdCoefftId;                         /*표준계수ID*/
    private String stdCoefftSe;                        /*표준계수구분*/
    private String stdCoefftSeNm;                   /*표준계수구분명*/
    private String stdCoefftCtgy;                     /*표준계수유형*/
    private String stdCoefftCtgyNm;               /*표준계수유형명*/
    private String stdCoefftNm;						  /*표준계수명*/
    private String stdCoefftEngNm;                 /*표준계수영문명*/
    private String mesCyc;                             /*측정주기*/
    private String mesCycNm;                        /*측정주기명*/
    private String typeGbn;							 /*망대망소구분*/
    private String typeGbnNm;					     /*망대망소구분명*/
    private String calcObj;                              /*산출목적*/
    private String calcUnt;                              /*산출단위*/
    private String calcUntNm;                         /*산출단위명*/
    private String stdCoefftDef;                      /*표준계수정의*/
    private String stdCoefftFm;						 /*표준계수수산식*/
    private String anlzMrtId;							 /*분석마트ID*/
    private String delYn;								 /*삭제여부*/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime rgstDt;                              	 /*등록일자*/
    private String rgstId;                              	 /*등록ID*/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime modiDt;                           	 /*수정일자*/
    private String modiId;                              /*수정ID*/
    private String userId;								 /*담당자명 ID*/
    private String mngUserNm;                      /*담당자명*/
    private String basRptId;                           /*기본보고서 ID*/
    private String basRptNm;                        /*기본보고서*/
    private String relRptId;                         /*관련보고서 ID*/
    private String relRptNm;                         /*관련보고서*/
    private String martNm;                          /*마트명*/
    private String fileNm;                      	   /*첨부파일명*/
    private String fndTagId;						    /*검색태그 ID*/
    private String fndTagNm;                        /*검색태그명*/
    private String deptCd;						       /*관리부서 ID*/
    private String deptNm;						   /*부서명*/
    private String appOpin;                          /*신청의견*/
    private ArrayList<String> userIdList;
    private ArrayList<String> deptCdList;
    private ArrayList<String> rptList;
    private ArrayList<String> fndTagList;
    private String[] fileIds;
    private String rowNum;
    private int seq;								/*표준계수이력일련번호*/
}
