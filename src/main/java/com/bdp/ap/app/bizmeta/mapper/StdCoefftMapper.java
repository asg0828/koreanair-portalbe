package com.bdp.ap.app.bizmeta.mapper;

import java.util.List;

import com.bdp.ap.app.bizmeta.model.AnalysisMartModel;
import com.bdp.ap.app.bizmeta.model.BizmetaCriteria;
import com.bdp.ap.app.bizmeta.model.BizmetaModel;
import com.bdp.ap.app.bizmeta.model.RptModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;

/**
 * Mybatis StdCoefft 매핑 Interface
 */
@ConnMapperFirst
public interface StdCoefftMapper {
	public List<BizmetaModel>selectStdCoefftList(BizmetaCriteria criteria);
	public int selectStdCoefftListCount(BizmetaCriteria criteria);
	public long insertStdCoefft(BizmetaModel model);
	public long updateStdCoefft(BizmetaModel model);
	public long deleteStdCoefft(BizmetaModel model);
	public BizmetaModel selectStdCoefftDetail(BizmetaCriteria criteria);
	public int selectStdCoefftNmCount(BizmetaCriteria model);     //표준계수명 중복확인
	public List<BizmetaModel>selectStdCoefftDeptList(BizmetaCriteria criteria);   //표준계수명 부서표준계수조회
	public int selectStdCoefftDeptCount(BizmetaCriteria criteria);							//표준계수명 부서표준계수조회 총개수
	public List<RptModel>selectRptList(BizmetaCriteria criteria);							//보고서 선택 조회
	public List<AnalysisMartModel>selectAnalysisMartList(BizmetaCriteria criteria);	//분석마트 선택 조회
	public List<BizmetaModel>selectStdCoefftHistList(BizmetaCriteria criteria);     //표준계수이력조회
	public List<BizmetaModel>selectStdCoefftExcel(BizmetaCriteria criteria);
	public long insertStdCoefftHist(BizmetaModel model);			//표준계수이력저장
	public BizmetaModel selectStdCoefftHist(BizmetaCriteria criteria);
	public int selectStdCoefftNmListCount(BizmetaCriteria criteria);
	public List<BizmetaModel>selectStdCoefftNmList(BizmetaCriteria criteria);
	public int selectStdCoefftHistListCount(BizmetaCriteria criteria);
	public int selectStdCoefftHistSeq(String id);
	
}
