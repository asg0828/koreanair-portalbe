package com.bdp.ap.app.system.mapper;

import java.util.List;

import com.bdp.ap.app.system.model.VisibleReportModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;
import com.bdp.ap.common.paging.Criteria;

@ConnMapperFirst
public interface VisibleReportMapper {

  List<VisibleReportModel> selectVisibleReportList(Criteria criteria);

  int selectVisibleReportListCount(Criteria criteria);

  void insertVisibleReport(VisibleReportModel visibleReportModel);

  VisibleReportModel selectVisibleReport(VisibleReportModel visibleReportModel);

  void updateVisibleReport(VisibleReportModel visibleReportModel);

}
