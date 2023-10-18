package com.bdp.ap.app.audit.mapper;

import java.util.List;

import com.bdp.ap.app.audit.model.LogModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;
import com.bdp.ap.common.paging.Criteria;

/**
 * Mybatis 로그관리 매핑 Interface
 */
@ConnMapperFirst
public interface LogMapper {

	//접속 로그에서 프로그램 명 조회 (※LogUtil.java 에서 사용됨)
	String selectProgramNm(LogModel model);

	//사용자 접근로그 목록 추출
	List<LogModel> selectUserLogList(Criteria criteria);

	//사용자 접근로그 카운트
	int selectUserLogCount(Criteria criteria);

	//관리자 접근로그 목록 추출
	List<LogModel> selectMgrLogList(Criteria criteria);

	//관리자 접근로그 카운트
	int selectMgrLogCount(Criteria criteria);

	/**
	 * 로그 모델을 신규생성한다.
	 *
	 * @param model 로그모델
	 * @return
	 */
	long insert(LogModel model);

	/**
	 * Tableau 로그 모델을 신규생성한다.
	 *
	 * @param model 로그모델
	 * @return
	 */
	long insertTableau(LogModel model);
}
