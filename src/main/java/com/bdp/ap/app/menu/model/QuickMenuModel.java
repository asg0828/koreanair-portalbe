package com.bdp.ap.app.menu.model;

import java.time.LocalDateTime;

import org.json.JSONArray;
import org.json.JSONObject;

import com.bdp.ap.common.paging.Criteria;

import lombok.Data;

@Data
public class QuickMenuModel extends Criteria {
	private String quickUserId;				//퀵사용자ID
	private String menuId;					//메뉴ID
	private String menuNm;					//메뉴명
	private String menuUrl;					//메뉴URL
	private String menuDsc;					//메뉴설명
	private int ordSeq;						//순서
	private String menuSe;					//메뉴구분
	private String upMenuId;				//상위메뉴ID
	private String upMenuNm;				//상위메뉴명	
	private String upMenuUrl;				//상위메뉴URL	
	private String bookmarkYn;				//북마크여부
	private String delYn;					//삭제여부	
	private String rgstId; 					// 등록자ID
	private LocalDateTime rgstDt; 			// 등록일시
	private String modiId; 					// 수정자ID
	private LocalDateTime modiDt; 			// 수정일시

	private String companyCd;				//회사코드
	private int lv; 						// 레벨
	private JSONObject menuAttr; 			// 메뉴 속성
	private String useYn; // 사용 여부(메뉴용)
	private JSONArray fullPathId; // 전체 경로 메뉴 ID
	private JSONArray fullPathNm; // 전체 경로 메뉴 명
	private String fullOrdSeq; // 전체 정렬 순서
	private JSONObject menuAuthAttr; // 메뉴 속성
	private JSONArray treeJson;
}
