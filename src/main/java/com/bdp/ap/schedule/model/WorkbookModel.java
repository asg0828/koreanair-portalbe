package com.bdp.ap.schedule.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class WorkbookModel {
	private String tableauWorkbookId;
	private String tableauWorkbookNm;
	private String tableauWorkbookDsc;
	private String tableauWorkbookUrl;
	private String tableauWebUrl;
	private String tableauViewId;
	private String tableauProjectId;
	private String tableauUserId;
	private String fileId;
	private String fileUrl;
	private String useYn;
	private String modiSe;
	private String rgstId;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime rgstDt;
	private String modiId;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modiDt;
}
