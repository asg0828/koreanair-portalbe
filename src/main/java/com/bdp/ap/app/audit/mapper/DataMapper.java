package com.bdp.ap.app.audit.mapper;

import java.util.List;

import com.bdp.ap.app.audit.model.DataModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;
import com.bdp.ap.common.paging.Criteria;

/**
 * Mybatis 모니터링 관리 매핑 Interface
 */
@ConnMapperFirst
public interface DataMapper {

	//데이터 목록
	List<DataModel> selectDataList(Criteria criteria);

	//데이터 카운트
	int selectDataListCount(Criteria criteria);


}
