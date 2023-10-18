package com.bdp.ap.app.system.service;

import java.util.List;

import com.bdp.ap.app.system.model.VisibleReportModel;
import com.bdp.ap.common.paging.Criteria;

public interface VisibleReportService {

  public List<VisibleReportModel> selectVisibleReportList(Criteria criteria);

  public int selectVisibleReportListCount(Criteria criteria);

  public void insertVisibleReport(VisibleReportModel visibleReportModel);

  public VisibleReportModel selectVisibleReport(VisibleReportModel visibleReportModel);

  public void updateVisibleReport(VisibleReportModel visibleReportModel);

}
