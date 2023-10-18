package com.bdp.ap.common;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bdp.ap.common.service.IdService;

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
		return service.getId(Constant.ID.FILE);
	}

	/**
	 * 공지사항 ID : 공지사항
	 */
	public String getNoticeId() {
		return service.getId(Constant.ID.NOTICE);
	}

	/**
	 * 분석소스공유관리 ID : 분석소스공유관리
	 */
	public String getAnlzSourceId() {
		return service.getId(Constant.ID.ANLZ_SOURCE);
	}

	/**
	 * 도움말 ID : 도움말
	 */
	public String getHelpId() {
		return service.getId(Constant.ID.HELP);
	}

	/**
	 * FAQ ID : FAQ
	 */
	public String getFaqId() {
		return service.getId(Constant.ID.FAQ);
	}

	/**
	 * QNA ID : QNA
	 */
	public String getQnaId() {
		return service.getId(Constant.ID.QNA);
	}

	/**
	 * 자유게시판 ID : 자유 게시판 ID
	 */
	public String getFreeId() {
		return service.getId(Constant.ID.FREE);
	}

	/**
	 * 권한 ID : 권한
	 */
	public String getAuthId() {
		return service.getId(Constant.ID.AUTH);
	}

	/**
	 * 메뉴 ID : 메뉴
	 */
	public String getMenuId() {
		return service.getId(Constant.ID.MENU);
	}

	/**
	 * 라이선스 ID : 라이선스
	 */
	public String getAlrmId() {
		return service.getId(Constant.ID.ALRM);
	}

	/**
	 * 외부 ID : 외부 시스템
	 */
	public String getExtrnlId() {
		return service.getId(Constant.ID.EXTERNAL);
	}

	/**
	 * 외부 데이터 ID : 외부 데이터
	 */
	public String getExtrnlDataId() {
		return service.getId(Constant.ID.EXTERNAL_DATA);
	}

	/**
	 * 업무 ID : 업무 카테고리
	 */
	public String getWrkId() {
		return service.getId(Constant.ID.WORK);
	}

	/**
	 * 프로젝트 ID : 프로젝트
	 */
	public String getProjectId() {
		return service.getId(Constant.ID.PROJECT);
	}

	/**
	 * 리포트 ID : 리포트
	 */
	public String getReportId() {
		return service.getId(Constant.ID.REPORT);
	}

	/**
	 * 승인 ID : 프로젝트 승인, 리포트 승인, 리포트 역할 승인, 리포트 외부 승인
	 */
	public String getAprvId() {
		return service.getId(Constant.ID.APPROVAL);
	}

	/**
	 * EAI 소켓 STND_COM_SEQ
	 */
	public String getEaiSocketStndComSeq() {
		return service.getTimeSeq(Constant.ID.EAI_SOCKET);
	}

	/**
	 * 화면 도움말 ID : 도움말
	 */
	public String getScreenHelpId() {
		return service.getId(Constant.ID.SCREEN_HELP);
	}

	/**
	 * 시간화 보고서 관리 ID : 시각화보고서
	 */
	public String getVisibleReportId() {
		return service.getId(Constant.ID.VISIBLE_REPORT);
	}

	/**
	 * 시간화 보고서 관리 ID : 시각화보고서
	 */
	public String getBatchId() {
		return service.getId(Constant.ID.BATCH_JOB);
	}

	/**
	 * 표준계수ID
	 */
	public String getStdCoefftId() {
		return service.getId(Constant.ID.STD_COEFFT_ID);
	}

	/**
	 * 보고서정보ID
	 */
	public String getRptInfId() {
		return service.getId(Constant.ID.RPT_INF_ID);
	}

	/**
	 * 분석마트ID
	 */
	public String getAnlzmrtId() {
		return service.getId(Constant.ID.ANLZ_MRT_ID);
	}

	/**
	 * 보고서ID
	 */
	public String getRptId() {
		return service.getId(Constant.ID.RPT_ID);
	}

	/**
	 * 디멘젼ID
	 */
	public String getDimId() {
		return service.getId(Constant.ID.DIM_ID);
	}

	/**
	 * 메젼ID
	 */
	public String getMesId() {
		return service.getId(Constant.ID.MES_ID);
	}

	/**
	 * 키항목ID
	 */
	public String getKeyItmId() {
		return service.getId(Constant.ID.KEY_ITM_ID);
	}

	/**
	 * 데이터마트ID
	 */
	public String getDtMrtId() {
		return service.getId(Constant.ID.DT_MRT_ID);
	}

	/**
	 * BizMeta FILE ID
	 */
	public String getBizFileId() {
		return service.getId(Constant.ID.BIZ_FILE_ID);
	}

	/**
	 * 관리사용자ID
	 */
	public String getMngUserId() {
		return service.getId(Constant.ID.MNG_USER_ID);
	}

	/**
	 * 기피부서ID
	 */
	public String getAvoidDeptCd() {
		return service.getId(Constant.ID.AVOID_DEPT_CD);
	}

	/**
	 * 관리부서ID
	 */
	public String getMngDeptCd() {
		return service.getId(Constant.ID.MNG_DEPT_CD);
	}

	/**
	 * 검색태그ID
	 */
	public String getFndTagId() {
		return service.getId(Constant.ID.FND_TAG_ID);
	}

	/**
	 * 관리정보ID
	 */
	public String getMngInfId() {
		return service.getId(Constant.ID.MNG_INF_ID);
	}

	/**
	 * 메타테이블ID
	 */
	public String getMetaTblId() {
		return service.getId(Constant.ID.META_TBL_ID);
	}

	/**
	 * 메타컬럼ID
	 */
	public String getMetaColId() {
		return service.getId(Constant.ID.META_COL_ID);
	}

	/**
	 * 포틀릿 ID
	 */
	public String getPortletId() {
		return service.getId(Constant.ID.PORTLET_ID);
	}

	/**
	 * 포틀릿 관리 ID
	 */
	public String getPortletMngId() {
		return service.getId(Constant.ID.PORTLET_MNG_ID);
	}
	
	/**
	 * 관리부서ID
	 */
	public String getDeptCd() {
		return service.getId(Constant.ID.DEPT_CD);
	}
	
	/**
	 * 직위ID
	 */
	public String getPstnCd() {
		return service.getId(Constant.ID.PSTN_CD);
	}
	
	/**
	 * 직급ID
	 */
	public String getRankCd() {
		return service.getId(Constant.ID.RANK_CD);
	}
	
	/**
	 * 직책ID
	 */
	public String getDutyCd() {
		return service.getId(Constant.ID.DUTY_CD);
	}
	
	/**
	 * 게시판 ID
	 * @return
	 */
	public String getBbsId() {
		return service.getId(Constant.ID.BBS_ID);
	}
	
	/*
	 * 게시판 항목 ID
	 */
	public String getBbsItem() {
		return service.getId(Constant.ID.BBS_ITEM);
	}
	
	/**
	 * 가상그룹 ID
	 */
	public String getVgroupId() {
		return service.getId(Constant.ID.VGROUP);
	}

	/**
	 * 결재템플릿 ID
	 */
	public String getAprvTemplateId() {
		return service.getId(Constant.ID.APRV_TEMPLATE_ID);
	}

	/**
	 * 결재라인 ID
	 */
	public String getAprvLineId() {
		return service.getId(Constant.ID.APRV_LINE_ID);
	}
	
	/**
	 * 결재요청 ID
	 */
	public String getAprvRqstId() {
		return service.getId(Constant.ID.APRV_RQST_ID);
	}
	
	/**
	 * 자료실 ID
	 */
	public String getDataId() {
		return service.getId(Constant.ID.DATA_ID);
	}

	/**
	 * 표준용어ID 
	 * @return
	 */
	public String getStandardTerms() {
		return service.getId(Constant.ID.STANDARD_TERMS);
	}

	/*
	 * 협업공간ID : 협업공간
	 */
	public String getCollabSpcId() {
		return service.getId(Constant.ID.COLLABORATE_SPACE);
	}

	/*
	 * 협업공간TASKID :협업공간
	 */
	public String getCollabSpcTskId() {
		return service.getId(Constant.ID.COLLABORATE_SPACE_TASK);
	}

	/*
	 * Comment ID :협업공간 
	 */
	public String getComtId() {
		return service.getId(Constant.ID.COMMENT);
	}
}
