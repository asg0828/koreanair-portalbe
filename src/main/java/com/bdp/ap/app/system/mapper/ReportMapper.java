package com.bdp.ap.app.system.mapper;

import java.util.List;

import com.bdp.ap.app.system.model.ReportModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;

@ConnMapperFirst
public interface ReportMapper {

    List<ReportModel> selectReportListType(ReportModel model);
	int selectReportListTypeCount(ReportModel model);
    ReportModel selectReport(ReportModel model);

}