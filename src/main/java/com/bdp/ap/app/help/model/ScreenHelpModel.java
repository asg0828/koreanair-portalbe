package com.bdp.ap.app.help.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ScreenHelpModel {
  private String screenHelpId;
  private String sj;
  private String cn;
  private String registYn;
  private String fileId;
  private String useYn;
  private int viewCnt;
  private String systemCd;
  private String mgrDeptCd;
  private String inputAttr;
  private String outAttr;
  private String[] fileIds;
  private String mgrDeptNm;

  private String rgstId;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDateTime rgstDt;
  private String modiId;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDateTime modiDt;
}
