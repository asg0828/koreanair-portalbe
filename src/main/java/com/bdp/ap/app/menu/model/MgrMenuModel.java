package com.bdp.ap.app.menu.model;

import java.time.LocalDateTime;

import org.json.JSONArray;
import org.json.JSONObject;

import lombok.Data;

/**
 * 메뉴모델
 *
 */
@Data
public class MgrMenuModel {
	private String menuId; // 메뉴ID
	private String upMenuId; // 상위메뉴ID 최상위 메뉴는 0으로 부터 시작한다.	
	private String menuNm; // 메뉴명
	private String menuUrl; // 메뉴URL
	private String menuDsc; // 메뉴설명
	private int ordSeq; // 메뉴순번 각상위메뉴 ID별 순번
	private String menuSe; // 메뉴 구분
	private JSONObject menuAttr; // 메뉴 속성
	private String useYn; // 사용 여부(메뉴용)
	private String rgstId; // 등록자ID
	private LocalDateTime rgstDt; // 등록일시
	private String modiId; // 수정자ID
	private LocalDateTime modiDt; // 수정일시
	private String delYn;  //삭제여부
	
	private int lv; // 레벨
	private JSONArray fullPathId; // 전체 경로 메뉴 ID
	private JSONArray fullPathNm; // 전체 경로 메뉴 명
	private String fullOrdSeq; // 전체 정렬 순서
	private JSONObject menuAuthAttr; // 메뉴 속성
	private JSONArray treeJson;
	private String authId;
	private String authUseYn;
	private String menuIcon;		//메뉴 아이콘
}
