package com.bdp.ap.app.bizmeta.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Bizmeta 분석모델
 */
@Data
public class AnalysisMartModel {

	private String anlzMrtId;					   	/*분석마트ID*/
	private String tblId;							   	/*테이블ID*/
	private String martNm;					   	/*마트명*/
	private String topicArea;					   	/*주제영역*/
	private String topicAreaNm;				   	/*주제영역명*/
	private String loadCyc;					  	/*적재주기*/
	private String loadCycNm;					  	/*적재주기*/
	private String def;							 	 /*정의*/
	private String usDtl;						 	 /*활용상세*/
	private String regId;							/*등록자*/
	private String regNm;							/*등록자명*/
	private String regDt;							/*등록일자*/
	private String delYn;							/*삭제여부*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime rgstDt;                          /*등록일자*/
    private String rgstId;                          /*등록ID*/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime modiDt;                        /*수정일자*/
    private String modiId;                        /*수정ID*/
    private String dimNm;                       /*주요조회조건*/
    private String mesNm;					      /*주요항목*/
    private String dataNm;					  /*데이터원천*/
    private String fndTagNm; 				 /*검색태그*/
    private String mngUserNm;             /*담당자*/
    private String deptNm;					/*부서명*/
    private String fileNm;						/*첨부파일명*/
    private String keyItmNm;			   /*KEY항목명*/
    private String avoidDeptNm;		    /*기피부서명*/
    private String appOpin;					/*신청의견*/
    private String opnYn;						/*공개여부*/
    private String userId;

    private ArrayList<String> keyList;			 /*key항목*/
    private ArrayList<String> dimList;			 /*디멘젼*/
    private ArrayList<String> mesList;			 /*메젼*/
    private ArrayList<String> dtMrtList;			 /*데이터원천*/
    private ArrayList<String> mngDeptList;           /*관리부서*/
    private ArrayList<String> avoidDeptList;  /*기피관리부서*/
    private ArrayList<String> fndTagList;           /*검색태그*/
    private String[] fileIds;
    private String rowNum;

}
