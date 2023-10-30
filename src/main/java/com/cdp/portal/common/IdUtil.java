package com.cdp.portal.common;

import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

import com.cdp.portal.common.constants.CommonConstants;
import com.cdp.portal.common.service.IdService;

/**
 * ID 생성 Util
 * ID 생성 규칙 : 13자리
 *    ID 접두사 2자리 + 년도 2자리 + 구분자 + 1자리 + 연번 9자리
 *    연번 관리 테이블 : T_ID_SN
 */
@Component
public class IdUtil {

	@Resource(name="idService")
	private IdService service;

	/**
	 * UUID 생성
	 * @since2020.12.16.
	 * @return String 생성된 UUID
	 */
	public String getUUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 파일 ID : 파일
	 */
	public String getFileId() {
		return service.getId(CommonConstants.ID.FILE);
	}

	/**
	 * 공지사항 ID : 공지사항
	 */
	public String getNoticeId() {
		return service.getId(CommonConstants.ID.NOTICE);
	}

	/**
	 * 분석소스공유관리 ID : 분석소스공유관리
	 */
	public String getAnlzSourceId() {
		return service.getId(CommonConstants.ID.ANLZ_SOURCE);
	}

	/**
	 * 도움말 ID : 도움말
	 */
	public String getHelpId() {
		return service.getId(CommonConstants.ID.HELP);
	}

	/**
	 * FAQ ID : FAQ
	 */
	public String getFaqId() {
		return service.getId(CommonConstants.ID.FAQ);
	}

	/**
	 * QNA ID : QNA
	 */
	public String getQnaId() {
		return service.getId(CommonConstants.ID.QNA);
	}

	/**
	 * 자유게시판 ID : 자유 게시판 ID
	 */
	public String getFreeId() {
		return service.getId(CommonConstants.ID.FREE);
	}

	/**
	 * 권한 ID : 권한
	 */
	public String getAuthId() {
		return service.getId(CommonConstants.ID.AUTH);
	}

	/**
	 * 메뉴 ID : 메뉴
	 */
	public String getMenuId() {
		return service.getId(CommonConstants.ID.MENU);
	}

	/**
	 * 라이선스 ID : 라이선스
	 */
	public String getAlrmId() {
		return service.getId(CommonConstants.ID.ALRM);
	}

	/**
	 * 리포트 ID : 리포트
	 */
	public String getReportId() {
		return service.getId(CommonConstants.ID.REPORT);
	}

	/**
	 * 화면 도움말 ID : 도움말
	 */
	public String getScreenHelpId() {
		return service.getId(CommonConstants.ID.SCREEN_HELP);
	}

	/**
	 * 시간화 보고서 관리 ID : 시각화보고서
	 */
	public String getBatchId() {
		return service.getId(CommonConstants.ID.BATCH_JOB);
	}

	/**
	 * 메타테이블ID
	 */
	public String getMetaTblId() {
		return service.getId(CommonConstants.ID.META_TBL_ID);
	}
	
	/**
	 * 관리부서ID
	 */
	public String getDeptCd() {
		return service.getId(CommonConstants.ID.DEPT_CD);
	}
	
	/**
	 * 게시판 ID
	 * @return
	 */
	public String getBbsId() {
		return service.getId(CommonConstants.ID.BBS_ID);
	}
	
	/*
	 * 게시판 항목 ID
	 */
	public String getBbsItem() {
		return service.getId(CommonConstants.ID.BBS_ITEM);
	}
	
	/**
	 * 가상그룹 ID
	 */
	public String getVgroupId() {
		return service.getId(CommonConstants.ID.VGROUP);
	}

	/**
	 * 자료실 ID
	 */
	public String getDataId() {
		return service.getId(CommonConstants.ID.DATA_ID);
	}

	/**
	 * 표준용어ID 
	 * @return
	 */
	public String getStandardTerms() {
		return service.getId(CommonConstants.ID.STANDARD_TERMS);
	}

}
