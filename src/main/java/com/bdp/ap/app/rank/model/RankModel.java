package com.bdp.ap.app.rank.model;

import java.time.LocalDateTime;

import org.json.JSONArray;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class RankModel {
	private String rankCode;
	private String upRankCode;
	private String rankNm;
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
	private String companyCode;       // 회사코드
	private String companyNm;       // 회사명
}
