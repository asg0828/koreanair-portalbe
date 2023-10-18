package com.bdp.ap.app.audit.mapper;

import java.util.List;

import com.bdp.ap.app.audit.model.AuditDataSetModel;
import com.bdp.ap.app.audit.model.UsingTableModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;
import com.bdp.ap.common.paging.Criteria;

/**
 * Mybatis 로그관리 매핑 Interface
 */
@ConnMapperFirst
public interface AuditDataSetMapper {

	//데이터셋 목록 추출
	List<AuditDataSetModel> selectDataSetList(Criteria criteria);

	//데이터셋 목록 카운트
	int selectDataSetCount(Criteria criteria);

	//데이터셋 > 사용 테이블 목록 추출
	List<UsingTableModel> selectUsingTableList(String datasetId);

	//데이터셋 > 사용 테이블 목록 카운트
	int selectUsingTableCount(Criteria criteria);

	/**
	 * 로그 모델을 신규생성한다.
	 *
	 * @param model 로그모델
	 * @return
	 */
	long insert(AuditDataSetModel model);

	/**
	 * Tableau 로그 모델을 신규생성한다.
	 *
	 * @param model 로그모델
	 * @return
	 */
	long insertTableau(AuditDataSetModel model);
}
