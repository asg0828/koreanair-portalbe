package com.bdp.ap.app.system.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;

import com.bdp.ap.common.paging.Criteria;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ProjectModel extends Criteria implements Serializable {

    private String projectId;
    private String ver;
    private String minorVer;
    private String projectNm;
    private String projectCl;
    private String projectTy;
    private String projectTyNm;
    private String projectSe;
    private String deptCode;
    private JSONObject pcptInfo;
    private JSONObject mgrInfo;
    private String projectDsc;
    private String projectStat;
    private int ordSeq;
    private String useYn;
    private String rgstId;
    private String startDt;
    private String endDt;
    
    //projectAprv
    private String deptNm;
    private String rownum;
    private String pstnNm;
    private String userNm;
    private String aprvDt;
    private String aprvStat;
    private String rjctResn;

    private JSONObject projectData;
    private JSONObject deptInfo;
    private String tableauProjectId;
    private JSONObject projectRsrc;
    private String activeYn;
    private String lastVerYn;
    private String mainPicrId;
    private String subPicrId;
    private String rqstResn;
    
    private String refId;
    private String refTy;
    private String roleSe;
    private String authYn;
    private String userId;
    private String nightUseYn;
    private String nightUseStartDt;
    private String nightUseEndDt;
    private String nightUseAprvStat;
    private String aprvId;
    private String aprvrId;
    
    private String startRqstDt;
	private String endRqstDt;
	private String mgrUserId;
	private String mgrUserNm;
	private String pcptUserId;
	private String pcptUserNm;
}