package com.bdp.ap.app.system.model;

import lombok.Data;

@Data
public class PortletModel {

  private String portletId; // 포틀릿 ID
  private String portletNm; // 포틀릿 명
  private String portletDsc; // 포틀릿 설명
  private String portletTypeCd; // 포틀릿 유형 코드(GROUP_ID=PORTLET_TYPE_CODE	포틀릿 타입)
  private String portletTypeNm; // 포틀릿 유형 명
  private String systemCd; // 시스템 코드(GGROUP_ID=SYSTEM_CD	시스템 코드)
  private String moveYn; // 이동 가능 여부
  private String portletLinkTypeCd; // 포틀릿 링크 유형 코드(GROUP_ID=PORTLET_LINK_TYPE_CD	포틀릿 링크 유형 코드)
  private String defaultViewUrl; // 기본 view URL
  private String moreViewYn; // 더보기 가능 여부
  private String moreViewUrl; // 더보기 view URL
  private String shareYn; // 공용여부
  private String useYn; // 사용 여부
  private String portletAuth; // 권한 범위,
  private String rgstId; // 등록 ID
  private String rgstDt; // 등록 일시
  private String modiId; // 수정 ID
  private String modiDt; // 수정 일시
}

