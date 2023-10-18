package com.bdp.ap.app.dept.model;

import java.time.LocalDateTime;
import java.util.List;

import org.json.JSONArray;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class DeptClModel {

	private String deptCode;
	private String upDeptCode;
	private String deptNm;
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
	private int lv;
	private JSONArray fullPathId;
	private JSONArray fullPathNm;
	private JSONArray fullOrdSeq;
	private String fullPathOrd;
	//태블로 배치에서 사용
	private String tableauProjectId;
	private String modiSe;
	private String companyCode;       // 회사코드
	private String companyNm;       // 회사명
}
