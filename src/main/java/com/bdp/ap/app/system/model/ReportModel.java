package com.bdp.ap.app.system.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;

import com.bdp.ap.common.paging.Criteria;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ReportModel extends Criteria implements Serializable {
	private int rownum;
    private String reportId;
    private String ver;
    private String projectId;
    private String projectNm;
    private String reportNm;
    private String reportCl;
    private String reportTy;
    private String reportTyNm;
    private String reportSe;
    private JSONObject wrkCat;
    private String reportDsc;
    private String reportStat;
    private String reportUrl;
    private JSONObject reportAttr;
    private String previewUrl;
    private String fileId;
    private String deptCode;
    private JSONObject pcptInfo;
    private JSONObject mgrInfo;
    private JSONObject sanctnInfo;
    private String rqstResn;
    private String executCycle;
    private String activeYn;
    private String atmcAprvYn;
    private String dashboardUrl;
    private String report_ty;
    private String dept_code;
    private String wrk_cat;
    private int ordSeq;
    private String useYn;
    private String rgstId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")    
    private LocalDateTime rgstDt;
    private String modiId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")    
    private LocalDateTime modiDt;
    private String orgnReportId;
    private String orngReportVer;
    private JSONObject deptInfo;
    private String deptMngYn;
    private String refId;
    private String refTy;
    private String roleSe;
    private String authYn;
    private String userId;
    private String tableauWorkbookId;
    private String viewCnt;
    
    private String userNm;
    private String pstnNm;
    private String deptNm;
    private String hdeptNm;
    private String userPhotoUrl;
    private String fileUrl;
    
    private String ownrId;
    private String ownrNm;
    private String ownrDeptNm;
    private String ownrPstnNm;
    private String ownrHdeptNm;
    
    private String showYn;
    private String tabName;
    
    private String mainPicrId;
    private String mainPicrNm;
    private String mainPicrDept;
    private String subPicrId;
    private String subPicrNm;
    private String subPicrDept;
    private String mainPhotoUrl;
    private String subPhotoUrl;
    private String authUserId;
    
}
