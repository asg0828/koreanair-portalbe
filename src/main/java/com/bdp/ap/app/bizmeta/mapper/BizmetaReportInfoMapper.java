package com.bdp.ap.app.bizmeta.mapper;

import java.util.List;

import com.bdp.ap.app.bizmeta.model.BizmetaCriteria;
import com.bdp.ap.app.bizmeta.model.ReportInfoModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;

@ConnMapperFirst
public interface BizmetaReportInfoMapper {
	public List<ReportInfoModel>selectReportInfoList(BizmetaCriteria criteria);
	public int selectReportInfoCount(BizmetaCriteria criteria);
	public long insertReportInfo(ReportInfoModel model);
	public ReportInfoModel selectReportInfoDetail(BizmetaCriteria criteria);
	public long updateReportInfo(ReportInfoModel model);
	public long deleteReportInfo(ReportInfoModel model);
	public List<ReportInfoModel>selectReportInfoExcel(BizmetaCriteria criteria);

}
