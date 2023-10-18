package com.bdp.ap.app.bizmeta.mapper;

import java.util.List;

import com.bdp.ap.app.bizmeta.model.AnalysisMartModel;
import com.bdp.ap.app.bizmeta.model.BizmetaCriteria;
import com.bdp.ap.common.annotation.ConnMapperFirst;

@ConnMapperFirst
public interface BizmetaAnalysisMartMapper {
	public List<AnalysisMartModel>selectAnalysisMartList(BizmetaCriteria criteria);
	public int selectAnalysisMartCount(BizmetaCriteria criteria);
	public List<AnalysisMartModel>selectAnalysisMartExcel(BizmetaCriteria criteria);
	public long insertAnalysisMart(AnalysisMartModel model);
	public AnalysisMartModel selectAnalysisMartDetail(BizmetaCriteria criteria);
	public long updateAnalysisMart(AnalysisMartModel model);
	public long deleteAnalysisMart(AnalysisMartModel model);

}
