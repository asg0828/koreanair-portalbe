package com.bdp.ap.app.system.model;

import java.util.List;

import lombok.Data;

@Data
public class PortletMngModel {

  private String portletMngId; // 포틀릿 관리 ID
  private String portletMngNm; // 포틀릿 관리 명
  private String portletMngDsc; // 포틀릿 관리 설명
  private String scrollType; // 스크롤 유형
  private String colCnt; // 열 갯수
  private String portletView; // 화면 목록
  private String rgstId; // 등록 ID
  private String rgstDt; // 등록 일시
  private String modiId; // 수정 ID
  private String modiDt; // 수정 일시
}

