package com.bdp.ap.app.board.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BbsModel {
	private String boardId;
	private String boardNm;
	private String typCd;
	private String typNm;
	private String description;
	private String urlAddr;
	private String wkTemplate;
	private String useYn;
	private String kindUseYn;
	private String findAuthYn;
	private String wkAuthYn;
	private String mngAuthYn;
	private String authNm;
	private String companyCode;
	private String delYn;
	private String rgstNm;
	private String rgstId;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime rgstDt;
	private String modiId;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modiDt;
	private String useYnNm;
		
	
	private String boardItemId;
	private String itemNm;
	private String itemPhyNm;
	private String itemUseYn;
	private String itemMarkYn;
	private String itemEssYn;
	
	private String boardKindId;
	private String kindNm;
	private int kindOrd;
	
	private String boardAuthId;
	private String authDivCd;
	
	private String[] itemIds;   		 //사용,필수 항목ID
	private String[] kindIds;          //분류ID
	private String[] findAuthIds;    //조회권한
	private String[] wkAuthIds;     //작성권한
	private String[] mngAuthIds;   //관리권한

	
	
	
}
