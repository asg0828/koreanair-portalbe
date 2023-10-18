package com.bdp.ap.app.bizmeta.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Bizmeta 보고서정보
 */
@Data
public class ReportInfoModel {

	private String rptInfId;								 /*보고서ID*/
	private String rptCl;								/*보고서구분*/
	private String rptClNm;                       /*보고서구분명*/
	private String rptCd;						    /*보고서코드*/
	private String rptNm;							/*보고서명*/
	private String topicArea;					    /*주제영역*/
	private String topicAreaNm;					/*주제영역명*/
	private String def;								/*정의*/
	private String usDtl;							/*활용상세*/
	private String usCyc;							/*활용주기*/
	private String usCycNm;	  				    /*활용주기명*/
	private String regId;							/*등록자*/
	private String delYn;							/*삭제여부*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime rgstDt;                          /*등록일자*/
    private String rgstId;                           /*등록ID*/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime modiDt;                        /*수정일자*/
    private String modiId;                        /*수정ID*/
    private String regNm;							/*등록자*/
    private String dimNm;                       /*주요조회조건*/
    private String mesNm;					      /*주요항목*/
    private String dataNm;					  /*데이터원천*/
    private String fndTagNm; 				 /*검색태그*/
    private String mngUserNm;             /*담당자명*/
    private String deptNm;					/*관리부서명*/
    private String avoidDeptNm;					/*기피부서명*/
    private String userId;						/*담당자명 ID*/
    private String opnYn;						/*공개여부*/
    private String opnYnNm;					/*공개여부명*/
    private String appOpin;					/*신청의견*/
    private String anlzMrtId;               /*관련분석마트Id*/
    private String martNm;                  /*관련분석마트명*/
    private String anlzMrtTblId;            /*관련분석마트테이블Id*/

    private ArrayList<String> dimList;			 /*디멘젼*/
    private ArrayList<String> mesList;			 /*메젼*/
    private ArrayList<String> dtMrtList;			 /*데이터원천*/
    private ArrayList<String> mngDeptList;           /*관리부서*/
    private ArrayList<String> avoidDeptList;  /*기피관리부서*/
    private ArrayList<String> fndTagList;           /*검색태그*/
    private String rowNum;
}








