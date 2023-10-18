package com.bdp.ap.app.board.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CommonBbsItemModel{

    private String boardItemId;
	private String itemNm;
	private String itemPhyNm;
    private String boardId;
	private String itemUseYn;
	private String itemMarkYn;
	private String itemEssYn;
    private String delYn;
	private String rgstId;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime rgstDt;
	private String modiId;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modiDt;

}