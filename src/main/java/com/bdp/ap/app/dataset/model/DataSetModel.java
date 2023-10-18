package com.bdp.ap.app.dataset.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class DataSetModel {
	private String tblInfoId;
	private String dataDivCd;
	private String dataDivNm;
	private String tblStCd;
	private String tblStNm; 
	private String sptGrpCd;
	private String sptGrpNm;
	private String bizDivCd;
	private String bizDivNm;
	private String lvlDivCd;
	private String lvlDivNm;
	private String sptCycCd;
	private String sptCycNm;
	private String sptCompCd;
	private String sptCompNm;
	private String preservDt;
	private String dbTyp;
	private String useTermCd;
	private String useTermNm;
	private String sptSiteNm;
	private String sptSiteAddr;
	private String tblKor;
	private String tblEng;
	private String tblDesc;
	private int usePropCnt;
	private int tblViewCnt;
	private String delYn;
 	private String rgstId;  
 	private String rgstNm;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rgstDt;  
    private String modiId;
    private String modiNm;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modiDt;  
    
    
	private String colInfoId;
	private String colId;
	private String colNm;
	private String colOrd;
	private String keyDivCd;
	private String keyDivNm;
	private String pkYn;
	private String dataTypNm;
	private String comCd;
	private String colDesc;
	
	
	private String hashTagId;
	private String hashTagNm;
	private String tagNm;
	
	
	private String dataComCdId;
	private String grpCompCd;
	private String grpCompNm;
	private String tblEngNm;
	private String tblKorNm;
	private String colEngNm;
	private String colKorNm;
	private String validVal;
	private String validNm;
	
	
	private String stdTermsPropId;
	private String termsNm;
	private String termsEng;
	private String termsAbb;
	private String propId;
	private String detailDesc;
	private String viewCnt;
	private String apprStCd;
	private String apprStNm;
	private String firstApprId;
	private String firstApprNm;
	private String secondApprId;
	private String secondApprNm;
	private String drafterId;
	private String drafterNm;
	
	
	private String tblUsePropId;
	private String dataSetTypCd;
	private String dataSetTypNm;
	private String propTitle;
	private String useObjDetail;
	private String infoPropCd;
	private String infoPropNm;
	private String useAfterInfoProc;
	private String useTermSdt;
	private String useTermEdt;
	private String useTermReason;
	private String useMethod;
	
	private String tblUseObjId; 
	private String useObjCd;
	
	private String rowNum;
}
