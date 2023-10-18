package com.bdp.ap.app.dataset.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.bdp.ap.common.paging.Criteria;

import lombok.Data;

@Data
public class DataSetCriteria extends Criteria{
	private String tblInfoId;
	private String sptGrpCd;
	private String bizDivCd;
	private String lvlDivCd;
	private String sptCycCd;
	private String sptCompCd;
	private String useTermCd;
	private String tblKor;
	private String tblEng;
	private String tblDesc;
	private String grpCompCd;
	private String stdTermsPropId;
	private String tblUsePropId;
	private String dataDivCd;
	
}
