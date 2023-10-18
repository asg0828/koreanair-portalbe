package com.bdp.ap.app.code.model;

import java.time.LocalDateTime;

import org.json.JSONArray;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 코드관리 모델
 */
@Data
public class PstnModel {

    private String pstnCode;            // 직위코드
    private String pstnNm;              // 직위명
    private String upPstnCode;
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
	
	private JSONArray fullPathId; // 전체 경로 메뉴 ID
	private JSONArray fullPathNm; // 전체 경로 메뉴 명
}
