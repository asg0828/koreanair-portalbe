package com.bdp.ap.app.board.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.bdp.ap.common.paging.Criteria;

import lombok.Data;

@Data
public class CommonBbsModel extends Criteria{

    private String boardId;
    private String id;
    private String bfId;
    private String sj;
    private String cn;
    private String viewCnt;
    // @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  startDt,endDt 모델명 중복문제로 임시주석처리
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	// private LocalDateTime startDt;
    // @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	// private LocalDateTime endDt;
    private String rgstDeptCd;
    private String rgstDeptNm;
    private String wkRgstId;
    private String wkRgstNm;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime wkDt;
    private String content;
    private String fileId;
    private String rlRptId;
    private String rlStdCoefftId;
    private String rlAnlzMrtId;
    private String rlViewId;
    private String rlUrl;
    private String delYn;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime rgstDt;
    private String rgstId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modiDt;
    private String modiId;

    private String viewType;
    private String[] fileIds;

    private String mgrAuthId;
    private String authId;
	
}
