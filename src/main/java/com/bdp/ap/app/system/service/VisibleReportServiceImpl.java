package com.bdp.ap.app.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdp.ap.app.system.mapper.VisibleReportMapper;
import com.bdp.ap.app.system.model.VisibleReportModel;
import com.bdp.ap.common.paging.Criteria;

@Service
public class VisibleReportServiceImpl implements VisibleReportService {

  @Autowired
  VisibleReportMapper visibleReportMapper;

  @Override
  public List<VisibleReportModel> selectVisibleReportList(Criteria criteria) {

    return visibleReportMapper.selectVisibleReportList(criteria);
  }

  @Override
public int selectVisibleReportListCount(Criteria criteria) {

    return visibleReportMapper.selectVisibleReportListCount(criteria);
  }

  @Override
  public void insertVisibleReport(VisibleReportModel visibleReportModel) {

    visibleReportMapper.insertVisibleReport(visibleReportModel);
  }

  @Override
  public VisibleReportModel selectVisibleReport(VisibleReportModel visibleReportModel) {

    return visibleReportMapper.selectVisibleReport(visibleReportModel);
  }

  @Override
  public void updateVisibleReport(VisibleReportModel visibleReportModel) {

    visibleReportMapper.updateVisibleReport(visibleReportModel);
  }

}
